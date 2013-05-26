package com.example.activities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.opengl.GLES20;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;
import com.example.data.Move;
import com.example.data.Randomon;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.particle.SpriteParticleSystem;
import org.andengine.entity.particle.emitter.PointParticleEmitter;
import org.andengine.entity.particle.initializer.*;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.particle.modifier.ColorParticleModifier;
import org.andengine.entity.particle.modifier.ExpireParticleInitializer;
import org.andengine.entity.particle.modifier.ScaleParticleModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.HorizontalAlign;

import java.util.ArrayList;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga
 *
 * @author Nicolas Gramlich
 * @since 11:54:51 - 03.04.2010
 */
public class Battle extends SimpleBaseGameActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    private static int CAMERA_WIDTH = 480;
    private static int CAMERA_HEIGHT = 320;

    // ===========================================================
    // Fields
    // ===========================================================


    private BitmapTextureAtlas mLeftRandomonTextureAtlas;
    private TiledTextureRegion mLeftRandomonTextureRegion;

    private BitmapTextureAtlas mRightRandomonTextureAtlas;
    private TiledTextureRegion mRightRandomonTextureRegion;

    Rectangle leftRandomonHitPoints;
    Rectangle rightRandomonHitPoints;

    private Font mFont;
    private Text leftRandomonText;
    private Text rightRandomonText;

    private Font mPlokFont;
    private Text resultText;

    private Randomon leftRandomon;
    private Randomon rightRandomon;

    private boolean leftAtacked;


    private int animation;
    AnimatedSprite banana;
    AnimatedSprite snapdragon;

    Entity commandsGroup;


    private BitmapTextureAtlas mBitmapTextureAtlas;
    private ITextureRegion mParticleTextureRegion;

    private boolean isBattle;

    private String wallpaper;

    private BitmapTextureAtlas mAutoParallaxBackgroundTexture;

    private ITextureRegion mParallaxLayerBack;
    private ITextureRegion mParallaxLayerMid;
    private ITextureRegion mParallaxLayerFront;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================



    @Override
    public EngineOptions onCreateEngineOptions() {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float camerah = displayMetrics.heightPixels;
        float cameraw = displayMetrics.widthPixels;
        final Camera camera = new Camera(0, 0, CAMERA_WIDTH,CAMERA_HEIGHT);
        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(cameraw, camerah), camera);
    }

    @Override
    public void onCreateResources() {

        this.animation=0;


        FontFactory.setAssetBasePath("font/");

        final ITexture plokFontTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);

        this.mPlokFont = FontFactory.createFromAsset(this.getFontManager(), plokFontTexture, this.getAssets(), "Plok.ttf", 32, true, Color.BLACK);
        this.mPlokFont.load();

        this.mFont = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 12);
        this.mFont.load();

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");



        this.mLeftRandomonTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 2048, 2048);
        this.mLeftRandomonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mLeftRandomonTextureAtlas, this, "ldpi/randomon2.png", 0, 0, 4, 4);
        this.mLeftRandomonTextureAtlas.load();

        this.mRightRandomonTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 2048, 2048);
        this.mRightRandomonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mRightRandomonTextureAtlas, this, "ldpi/randomon2.png", 0, 0, 4, 4);
        this.mRightRandomonTextureAtlas.load();

        ArrayList<Move> moves1 = new ArrayList<Move>();
        moves1.add(new Move("fire",R.drawable.tetrauros,50));
        ArrayList<Move> moves2 = new ArrayList<Move>();
        moves2.add(new Move("water",R.drawable.tetrauros,40));

        this.leftRandomon = new Randomon("Randomon 1 ","Normal", 40, 30, 60, 1.1, 200, 13,200 ,19, "doente","randomom muito bonito" ,1,1,moves1);
        this.rightRandomon =  new Randomon("Randomina", "Normal" , 60, 40, 30, 1.1, 200, 13, 200,31,"sedutora","randomomina muito sexy",1,1,moves2);

        this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 32, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mParticleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "particle_fire.png", 0, 0);

        this.mEngine.getTextureManager().loadTexture(this.mBitmapTextureAtlas);

        this.mAutoParallaxBackgroundTexture = new BitmapTextureAtlas(this.getTextureManager(), 1024, 1024);
        this.mParallaxLayerFront = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mAutoParallaxBackgroundTexture, this, "parallax_background_layer_front.png", 0, 0);
        this.mParallaxLayerBack = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mAutoParallaxBackgroundTexture, this, "parallax_background_layer_back.png", 0, 188);
        this.mParallaxLayerMid = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mAutoParallaxBackgroundTexture, this, "parallax_background_layer_mid.png", 0, 669);
        this.mAutoParallaxBackgroundTexture.load();


    }




    @Override
    public Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        final Scene scene = new Scene();
        scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));

        final VertexBufferObjectManager vertexBufferObjectManager = this.getVertexBufferObjectManager();

        final AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
        autoParallaxBackground.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(0.0f, new Sprite(0, CAMERA_HEIGHT - this.mParallaxLayerBack.getHeight(), this.mParallaxLayerBack, vertexBufferObjectManager)));
        autoParallaxBackground.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(-5.0f, new Sprite(0, 80, this.mParallaxLayerMid, vertexBufferObjectManager)));
        autoParallaxBackground.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(-10.0f, new Sprite(0, CAMERA_HEIGHT - this.mParallaxLayerFront.getHeight(), this.mParallaxLayerFront, vertexBufferObjectManager)));
        scene.setBackground(autoParallaxBackground);

        this.resultText = new Text(140, 300, this.mPlokFont, "Result text", vertexBufferObjectManager);
        scene.attachChild(resultText);
        resultText.setVisible(false);

		/* Snapdragon. */
        snapdragon = new AnimatedSprite(32, CAMERA_HEIGHT-128,128,128,this.mLeftRandomonTextureRegion, this.getVertexBufferObjectManager());
        snapdragon.animate(new long[] { 600, 200 }, 0, 1, true);

        scene.attachChild(snapdragon);

		/* Funny banana. */
        banana = new AnimatedSprite(CAMERA_WIDTH-160, CAMERA_HEIGHT-128,128,128,  this.mRightRandomonTextureRegion, this.getVertexBufferObjectManager());
        banana.animate(new long[] { 600, 200 }, 0, 1, true);
        scene.attachChild(banana);

        /* Create the rectangles. */
        final Rectangle rect1 = this.makeColoredRectangle(-60, -60, 1, 0, 0);
        final Rectangle rect2 = this.makeColoredRectangle(0, -60, 0, 1, 0);
        final Rectangle rect3 = this.makeColoredRectangle(0, 0, 0, 0, 1);
        final Rectangle rect4 = this.makeColoredRectangle(-60, 0, 1, 1, 0);

        commandsGroup = new Entity(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2);

        commandsGroup.attachChild(rect1);
        commandsGroup.attachChild(rect2);
        commandsGroup.attachChild(rect3);
        commandsGroup.attachChild(rect4);

        scene.attachChild(commandsGroup);

        scene.registerTouchArea(rect1);
        scene.registerTouchArea(rect2);
        scene.registerTouchArea(rect3);
        scene.registerTouchArea(rect4);


        Rectangle redLife1 = this.makeLifeRectangle(-200, 20,150, 1, 0, 0);
        leftRandomonHitPoints = this.makeLifeRectangle(-200, 20,150, 0, 1, 0);
        Rectangle redLife2 = this.makeLifeRectangle(50, 20, 150,1,0, 0);
        rightRandomonHitPoints = this.makeLifeRectangle(50,20 ,150, 0, 1, 0);
        drawHitpoints();

        String text1 = leftRandomon.getName()+" lvl."+leftRandomon.getLevel()+" "+" ("+leftRandomon.getCurrent_hitpoints()+"/"+leftRandomon.getHitpoints()+")";
        String text2 = rightRandomon.getName()+" lvl."+rightRandomon.getLevel()+" "+" ("+rightRandomon.getCurrent_hitpoints()+"/"+rightRandomon.getHitpoints()+")";
        leftRandomonText = new Text(-200, 0, this.mFont, text1 , new TextOptions(HorizontalAlign.LEFT), vertexBufferObjectManager);
        rightRandomonText = new Text(50, 0, this.mFont, text2, new TextOptions(HorizontalAlign.RIGHT), vertexBufferObjectManager);
        //updateText();

        Entity lifeGroup = new Entity(CAMERA_WIDTH / 2, 20);

        lifeGroup.attachChild(redLife1);
        lifeGroup.attachChild(leftRandomonHitPoints);
        lifeGroup.attachChild(redLife2);
        lifeGroup.attachChild(rightRandomonHitPoints);
        lifeGroup.attachChild(leftRandomonText);
        lifeGroup.attachChild(rightRandomonText);

        scene.attachChild(lifeGroup);



        scene.setTouchAreaBindingOnActionDownEnabled(true);



     /*
        ButtonSprite button = new  ButtonSprite(new ButtonSprite(25, 310, multiplayerTextureRegion, mEngine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if(pTouchEvent.isActionDown()) {
                    mainScene.detachChild(menu);
                    mainScene.detachChild(multiplayerbutton);
                }
                return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
               */

        final SpriteParticleSystem particleSystem = new SpriteParticleSystem(new PointParticleEmitter(0, CAMERA_HEIGHT), 6, 10, 200, this.mParticleTextureRegion, this.getVertexBufferObjectManager());
        particleSystem.addParticleInitializer(new BlendFunctionParticleInitializer<Sprite>(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE));
        particleSystem.addParticleInitializer(new VelocityParticleInitializer<Sprite>(15, 22, 1, 1));
        particleSystem.addParticleInitializer(new AccelerationParticleInitializer<Sprite>(5, 1));
        //particleSystem.addParticleInitializer(new RotationParticleInitializer<Sprite>(0.0f, 360.0f));
        particleSystem.addParticleInitializer(new ColorParticleInitializer<Sprite>(1.0f, 0.0f, 0.0f));
        particleSystem.addParticleInitializer(new ExpireParticleInitializer<Sprite>(11.5f));

        //particleSystem.addParticleModifier(new ScaleParticleModifier<Sprite>(0, 5, 0.5f, 2.0f));
        //particleSystem.addParticleModifier(new AlphaParticleModifier<Sprite>(2.5f, 3.5f, 1.0f, 0.0f));
        //particleSystem.addParticleModifier(new AlphaParticleModifier<Sprite>(3.5f, 4.5f, 0.0f, 1.0f));
        particleSystem.addParticleModifier(new ColorParticleModifier<Sprite>(0.0f, 11.5f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        //particleSystem.addParticleModifier(new AlphaParticleModifier<Sprite>(4.5f, 11.5f, 1.0f, 1.0f));

        scene.attachChild(particleSystem);

        return scene;
    }


    // ===========================================================
    // Methods
    // ===========================================================

    private Rectangle makeColoredRectangle(final float pX, final float pY, final float pRed, final float pGreen, final float pBlue) {

        final Rectangle coloredRect = new Rectangle(pX, pY, 60, 60, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if(pSceneTouchEvent.isActionDown()) {
                    movimento();
                }
                return true;
            }
        };

        coloredRect.setColor(pRed, pGreen, pBlue);
        return coloredRect;
    }

    private Rectangle makeLifeRectangle(final float pX, final float pY,final int distance , final float pRed, final float pGreen, final float pBlue) {
        final Rectangle coloredRect = new Rectangle(pX, pY, distance, 15, this.getVertexBufferObjectManager());
        coloredRect.setColor(pRed, pGreen, pBlue);
        return coloredRect;
    }

    public void movimento() {

        AnimatedSprite.IAnimationListener anime = new AnimatedSprite.IAnimationListener() {
            @Override
            public void onAnimationStarted(AnimatedSprite pAnimatedSprite, int pInitialLoopCount) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite, int pOldFrameIndex, int pNewFrameIndex) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite, int pRemainingLoopCount, int pInitialLoopCount) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
                updateHitPoints();
                //To change body of implemented methods use File | Settings | File Templates.
                movimento();

            }
        };
        this.animation++;
        switch(this.animation) {
            case 0:
                commandsGroup.setVisible(true);
                if(leftRandomon.getCurrent_hitpoints()>0){
                    snapdragon.animate(new long[] { 600, 200 }, 0, 1, true);
                }else{
                    commandsGroup.setVisible(false);
                }
                if(rightRandomon.getCurrent_hitpoints()>0){
                    banana.animate(new long[] { 600, 200 }, 0, 1, true);
                }else{
                    commandsGroup.setVisible(false);
                }
                break;
            case 1:
                // Left player atacks
                leftAtacked=true;
                snapdragon.animate(new long[] { 100, 100, 100 }, 8, 10, 5);
                banana.animate(new long[] { 100, 100 }, 5, 6, 5,anime);
                commandsGroup.setVisible(false);

                break;
            case 2:
                // Righs side player atacks
                leftAtacked=false;
                snapdragon.animate(new long[] { 100, 100 }, 11, 12, 5,anime);
                banana.animate(new long[] { 100, 100, 100 }, 2, 4, 5);
                commandsGroup.setVisible(false);

                this.animation=-1;

                break;


        }
    }

    private void updateHitPoints(){
        if(leftAtacked){
            leftRandomon.setCurrent_hitpoints(leftRandomon.getCurrent_hitpoints()-rightRandomon.getAttack());
        }else{
            rightRandomon.setCurrent_hitpoints(rightRandomon.getCurrent_hitpoints()-leftRandomon.getAttack());
        }

        drawHitpoints();
        updateText();
    }

    private void drawHitpoints(){
        if (leftRandomon.getCurrent_hitpoints()>0){
            leftRandomonHitPoints.setWidth(leftRandomon.getCurrent_hitpoints()*150/leftRandomon.getHitpoints());
        }
        else{
            leftRandomonHitPoints.setVisible(false);
            youLost();
        }
        if (rightRandomon.getCurrent_hitpoints()>0)
            rightRandomonHitPoints.setWidth(rightRandomon.getCurrent_hitpoints()*150/rightRandomon.getHitpoints());
        else{
            rightRandomonHitPoints.setVisible(false);
            youWin();
        }
    }

    private void youWin(){
        banana.animate(new long[] { 100, 100, 100 }, 5, 7,false);
        commandsGroup.setVisible(false);
        resultText.setText("You Win!!!");
        resultText.setX(CAMERA_WIDTH / 2 - resultText.getWidth() / 2);
        resultText.setY(CAMERA_HEIGHT/2-resultText.getHeight()/2);
        resultText.setVisible(true);
    }

    private void youLost(){
        snapdragon.animate(new long[] { 100, 100,100 }, 11, 13, false);
        commandsGroup.setVisible(false);
        resultText.setText("You Lose!!!");
        resultText.setX(CAMERA_WIDTH / 2 - resultText.getWidth() / 2);
        resultText.setY(CAMERA_HEIGHT / 2 - resultText.getHeight() / 2);
        resultText.setVisible(true);
    }

    private String initWallpaper(){
        return "";
    }

    private void updateText(){
        String text1 = leftRandomon.getName()+" lvl."+leftRandomon.getLevel()+" "+" ("+leftRandomon.getCurrent_hitpoints()+"/"+leftRandomon.getHitpoints()+")";
        String text2 = rightRandomon.getName()+" lvl."+rightRandomon.getLevel()+" "+" ("+rightRandomon.getCurrent_hitpoints()+"/"+rightRandomon.getHitpoints()+")";
        leftRandomonText.setText(text1);
        rightRandomonText.setText(text2);


    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
