package com.example.activities;

import android.graphics.Typeface;
import android.util.Log;
import com.example.data.Randomon;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.debug.Debug;

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

    private static final int CAMERA_WIDTH = 480;
    private static final int CAMERA_HEIGHT = 320;

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

    private Randomon leftRandomon;
    private Randomon rightRandomon;

    private boolean leftAtacked;


    private int animation;
    AnimatedSprite banana;
    AnimatedSprite snapdragon;

    Entity commandsGroup;

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
        final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
    }

    @Override
    public void onCreateResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        this.animation=0;

        this.mLeftRandomonTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 2048, 2048);
        this.mLeftRandomonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mLeftRandomonTextureAtlas, this, "0randomon1.png", 0, 0, 4, 4);
        this.mLeftRandomonTextureAtlas.load();

        this.mRightRandomonTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 2048, 2048);
        this.mRightRandomonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mRightRandomonTextureAtlas, this, "0randomon1.png", 0, 0, 4, 4);
        this.mRightRandomonTextureAtlas.load();


        this.mFont = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 16);
        this.mFont.load();
        
        this.leftRandomon = new Randomon("Randomon 1 ", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal");
        this.rightRandomon =  new Randomon("Randomina", 60, 40, 30, 1.1, 200, 4, 100, 13, "Normal");


    }




    @Override
    public Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());

        final Scene scene = new Scene();
        scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));

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
        leftRandomonHitPoints = this.makeLifeRectangle(-200, 20,140, 0, 1, 0);
        Rectangle redLife2 = this.makeLifeRectangle(50, 20, 150,1,0, 0);
        rightRandomonHitPoints = this.makeLifeRectangle(50,20 ,140, 0, 1, 0);

        VertexBufferObjectManager vertexBufferObjectManager = this.getVertexBufferObjectManager();
        String text1 = leftRandomon.getName()+" lvl."+leftRandomon.getLevel()+" "+" ("+leftRandomon.getCurrent_hitpoints()+"/"+leftRandomon.getHitpoints()+")";
        String text2 = rightRandomon.getName()+" lvl."+rightRandomon.getLevel()+" "+" ("+rightRandomon.getCurrent_hitpoints()+"/"+rightRandomon.getHitpoints()+")";
        Text nameText1 = new Text(-200, 0, this.mFont, text1 , new TextOptions(HorizontalAlign.LEFT), vertexBufferObjectManager);
        Text nameText2 = new Text(50, 0, this.mFont, text2, new TextOptions(HorizontalAlign.LEFT), vertexBufferObjectManager);

        Entity lifeGroup = new Entity(CAMERA_WIDTH / 2, 20);

        lifeGroup.attachChild(redLife1);
        lifeGroup.attachChild(leftRandomonHitPoints);
        lifeGroup.attachChild(redLife2);
        lifeGroup.attachChild(rightRandomonHitPoints);
        lifeGroup.attachChild(nameText1);
        lifeGroup.attachChild(nameText2);

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
        return scene;
    }


    // ===========================================================
    // Methods
    // ===========================================================

    private Rectangle makeColoredRectangle(final float pX, final float pY, final float pRed, final float pGreen, final float pBlue) {
        Log.v("erro","criou rectangulo");

        final Rectangle coloredRect = new Rectangle(pX, pY, 60, 60, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if(pSceneTouchEvent.isActionDown()) {
                    movimento();
                }
                Log.v("erro","tocou");
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
                //movimento();
            }

            @Override
            public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
                //To change body of implemented methods use File | Settings | File Templates.
                movimento();
                if(leftAtacked){
                    updateHitPoints(true);

                }else {
                    updateHitPoints(false);

                }

            }
        };
        this.animation++;
        switch(this.animation) {
            case 0:
                snapdragon.animate(new long[] { 600, 200 }, 0, 1, true);
                banana.animate(new long[] { 600, 200 }, 0, 1, true);
                commandsGroup.setVisible(true);

            break;
            case 1:
                // Left player atacks
                snapdragon.animate(new long[] { 100, 100, 100 }, 8, 10, 5,anime);
                banana.animate(new long[] { 100, 100 }, 5, 6, 5);
                commandsGroup.setVisible(false);

                break;
            case 2:
                // Righs side player atacks
                snapdragon.animate(new long[] { 100, 100 }, 11, 12, 5,anime);
                banana.animate(new long[] { 100, 100, 100 }, 2, 4, 5);
                commandsGroup.setVisible(false);
                //updateHitPoints(false);

                this.animation=-1;

                break;


        }
    }

    private void updateHitPoints(boolean leftP){
        if(leftP){
            rightRandomon.setCurrent_hitpoints(rightRandomon.getCurrent_hitpoints()-leftRandomon.getAttack());

        }else{
            leftRandomon.setCurrent_hitpoints(leftRandomon.getCurrent_hitpoints()-rightRandomon.getAttack());

        }
        if (leftRandomon.getCurrent_hitpoints()>0){
            leftRandomonHitPoints.setWidth(leftRandomon.getCurrent_hitpoints()*150/leftRandomon.getHitpoints());
        }
        else
            leftRandomonHitPoints.setVisible(false);

        if (rightRandomon.getCurrent_hitpoints()>0)
            rightRandomonHitPoints.setWidth(rightRandomon.getCurrent_hitpoints()*150/rightRandomon.getHitpoints());
        else
            rightRandomonHitPoints.setVisible(false);


    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
