package com.example.activities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import com.example.data.Randomon;
import com.example.data.SharedData;
import com.example.others.RoundedPath;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.modifier.SequenceEntityModifier;
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
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.modifier.ease.EaseSineInOut;

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


                 /*add in final Code*/
    int soundIDFatal;
    boolean loadedFatal = false;
    SoundPool soundPoolFatal;
    int priority = 1;
    int no_loop = 0;
    float normal_playback_rate = 1f;

    int soundIDFire;
    boolean loadedFire = false;
    SoundPool soundPoolFire;

    int soundIDFlying;
    boolean loadedFlying = false;
    SoundPool soundPoolFlying;

    int soundIDWild;
    boolean loadedWild = false;
    SoundPool soundPoolWild;

    int soundIDBattle;
    int otherBattleSound;
    boolean loadedBattle = false;
    SoundPool soundPoolBattle;

    /*stop add*/


    private static int CAMERA_WIDTH = 480;
    private static int CAMERA_HEIGHT = 320;

    // ===========================================================
    // Fields
    // ===========================================================


    private BitmapTextureAtlas mLeftRandomonTextureAtlas;
    private TiledTextureRegion mLeftRandomonTextureRegion;

    private BitmapTextureAtlas mRightRandomonTextureAtlas;
    private TiledTextureRegion mRightRandomonTextureRegion;

    private BitmapTextureAtlas movesTextureAtlas;
    private TiledTextureRegion movesTextureRegion;

    private BitmapTextureAtlas captTextureAtlas;
    private TiledTextureRegion captTextureRegion;

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
    private SharedData shared = SharedData.getInstance();

    private boolean leftIsFirst;

    Scene scene;

    AnimatedSprite rightRandomonSprite;
    AnimatedSprite leftRandomonSprite;
    AnimatedSprite movesSprite;
    AnimatedSprite captSprite;


    Entity commandsGroup;
    Rectangle moveBox1;
    Rectangle moveBox2;
    Rectangle moveBox3;
    Rectangle moveBox4;
    Rectangle captureBox;

    int LeftMove;

    private BitmapTextureAtlas mBitmapTextureAtlas;
    private ITextureRegion mParticleTextureRegion;



    private BitmapTextureAtlas mAutoParallaxBackgroundTexture;

    private ITextureRegion mParallaxLayerBack;
    private ITextureRegion mParallaxLayerMid;
    private ITextureRegion mParallaxLayerFront;
    private boolean isBattleOver=false;


    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    final int MOVE_ONE=0;
    final int MOVE_TWO=1;
    final int MOVE_THREE=2;
    final int MOVE_FOUR=3;
    final int CAPTURE = 4;


    int step;
    final int SPRITE_INICIO=0;
    final int SPRITE_AFTER_INICIO=1;
    final int SPRITE_AFTER_ATTACK1=2;
    final int SPRITE_AFTER_MOVE1=3;
    final int SPRITE_AFTER_DEFENSE1=4;
    final int SPRITE_AFTER_PAUSE=5;
    final int SPRITE_AFTER_ATTACK2=6;
    final int SPRITE_AFTER_MOVE2=7;
    final int SPRITE_AFTER_DEFENSE2=8;
    final int SPRITE_CAPTURE = 9;

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

        this.step=0;

        doSound();




        FontFactory.setAssetBasePath("font/");

        final ITexture plokFontTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 256, TextureOptions.BILINEAR);

        this.mPlokFont = FontFactory.createFromAsset(this.getFontManager(), plokFontTexture, this.getAssets(), "Plok.ttf", 32, true, Color.BLACK);
        this.mPlokFont.load();

        this.mFont = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 12);
        this.mFont.load();

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");


        int r1 = (int)Math.floor(Math.random()*6)+1;
        this.mLeftRandomonTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 2048, 2048);
        this.mLeftRandomonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mLeftRandomonTextureAtlas, this, "ldpi/randomon"+r1+".png", 0, 0, 4, 4);
        this.mLeftRandomonTextureAtlas.load();

        int r2 = (int)Math.floor(Math.random()*6)+1;

        this.mRightRandomonTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 2048, 2048);
        this.mRightRandomonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mRightRandomonTextureAtlas, this, "ldpi/randomon"+r2+".png", 0, 0, 4, 4);
        this.mRightRandomonTextureAtlas.load();


        this.movesTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 1024, 1024);
        this.movesTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.movesTextureAtlas, this, "packattack.png", 0, 0, 4, 4);
        this.movesTextureAtlas.load();

        this.captTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 1024, 1024);
        this.captTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.captTextureAtlas, this, "fl.png", 0, 0, 1, 1);
        this.captTextureAtlas.load();


        this.leftRandomon = (new Randomon("Catzinga", Randomon.NORMAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.catzinga,1));
        this.rightRandomon = (new Randomon("Canibalape", Randomon.NORMAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.canibalape,2));


        this.mAutoParallaxBackgroundTexture = new BitmapTextureAtlas(this.getTextureManager(), 1024, 1024);
        this.mParallaxLayerFront = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mAutoParallaxBackgroundTexture, this, "parallax_background_layer_front.png", 0, 0);
        this.mParallaxLayerBack = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mAutoParallaxBackgroundTexture, this, "parallax_background_layer_back.png", 0, 188);
        this.mParallaxLayerMid = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mAutoParallaxBackgroundTexture, this, "parallax_background_layer_mid.png", 0, 669);
        this.mAutoParallaxBackgroundTexture.load();


    }

/*add Sound*/
   public void doSound(){

       soundPoolFatal = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
       soundPoolFatal.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
           @Override
           public void onLoadComplete(SoundPool soundPool, int sampleId,
                                      int status) {
               loadedFatal = true;
           }
       });
       soundIDFatal = soundPoolFatal.load(this, R.raw.fatal_bomb, 1);


       soundPoolFire = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
       soundPoolFire.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
           @Override
           public void onLoadComplete(SoundPool soundPool, int sampleId,
                                      int status) {
               loadedFire = true;
           }
       });
       soundIDFire = soundPoolFire.load(this, R.raw.fatal_bomb2, 1);


       soundPoolFlying = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
       soundPoolFlying.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
           @Override
           public void onLoadComplete(SoundPool soundPool, int sampleId,
                                      int status) {
               loadedFlying = true;
           }
       });
       soundIDFlying = soundPoolFlying.load(this, R.raw.flying_blade, 1);


       soundPoolWild = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
       soundPoolWild.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
           @Override
           public void onLoadComplete(SoundPool soundPool, int sampleId,
                                      int status) {
               loadedWild = true;
           }
       });
       soundIDWild = soundPoolWild.load(this, R.raw.wild_wind2, 1);

       soundPoolBattle = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
       soundPoolBattle.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
           @Override
           public void onLoadComplete(SoundPool soundPool, int sampleId,
                                      int status) {
               loadedBattle = true;
           }
       });

       soundIDBattle = soundPoolBattle.load(this, R.raw.battlesound, 1);


   }
    /*add Sound*/

    @Override
    public Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        scene = new Scene();
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

        leftRandomonSprite = new AnimatedSprite(32, CAMERA_HEIGHT-128,128,128,this.mLeftRandomonTextureRegion, this.getVertexBufferObjectManager());
        scene.attachChild(leftRandomonSprite);
        rightRandomonSprite = new AnimatedSprite(CAMERA_WIDTH-160, CAMERA_HEIGHT-128,128,128,  this.mRightRandomonTextureRegion, this.getVertexBufferObjectManager());
        scene.attachChild(rightRandomonSprite);

        movesSprite = new AnimatedSprite(CAMERA_WIDTH/2, CAMERA_HEIGHT/2,64,64,  this.movesTextureRegion, this.getVertexBufferObjectManager());
        movesSprite.setVisible(false);
        scene.attachChild(movesSprite);

        captSprite = new AnimatedSprite(CAMERA_WIDTH/2, CAMERA_HEIGHT/2,64,64,  this.captTextureRegion, this.getVertexBufferObjectManager());
        captSprite.setVisible(false);
        scene.attachChild(captSprite);


        movimentoInicial();

        /* Create the rectangles. */
        captureBox = this.makeColoredRectangle(-200, -90, 45, 45, 0.88f, 0.88f, 0.88f,CAPTURE);
        moveBox1 = this.makeColoredRectangle(-90, -90, 80, 80, 0.88f, 0.88f, 0.88f,MOVE_ONE);
        moveBox2 = this.makeColoredRectangle( 10, -90, 80, 80, 0.88f, 0.88f, 0.88f,MOVE_TWO);
        moveBox3 = this.makeColoredRectangle(10, 10, 80, 80, 0.88f, 0.88f, 0.88f, MOVE_THREE);
        moveBox4 = this.makeColoredRectangle(-90, 10, 80, 80, 0.88f, 0.88f, 0.88f,MOVE_FOUR);
        final Text captureTxt = new Text(-200, -90, this.mFont, "Capture", new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
        captureTxt.setSize(45, 45);
        final Text move1 = new Text(-90, -90, this.mFont, "Wild Wind", new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
        final Text move2 = new Text(10, -90, this.mFont, "Fatal Bomb" , new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
        final Text move3 = new Text(10, 10, this.mFont,  "Flying Blade", new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
        final Text move4 = new Text(-90, 10, this.mFont, "Fire Ball" , new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);

        Sprite captureSprite = new Sprite(-200, -100,TextureRegionFactory.extractFromTexture(this.captTextureAtlas,0,0,125,125), vertexBufferObjectManager){
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if(pSceneTouchEvent.isActionDown()) {
                    atacar(CAPTURE);
                }
                return true;
            }
        };
        Sprite s1 = new Sprite(-90, -80,TextureRegionFactory.extractFromTexture(this.movesTextureAtlas,0,0,125,125), vertexBufferObjectManager){
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if(pSceneTouchEvent.isActionDown()) {
                    atacar(MOVE_ONE);
                }
                return true;
            }
        };
        Sprite s2 = new Sprite( 10, -80,TextureRegionFactory.extractFromTexture(this.movesTextureAtlas,0,125,125,125), vertexBufferObjectManager){
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if(pSceneTouchEvent.isActionDown()) {
                    atacar(MOVE_TWO);
                }
                return true;
            }
        };
        Sprite s3 = new Sprite( 10,  20,TextureRegionFactory.extractFromTexture(this.movesTextureAtlas,0,250,125,125), vertexBufferObjectManager){
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if(pSceneTouchEvent.isActionDown()) {
                    atacar(MOVE_THREE);
                }
                return true;
            }
        };
        Sprite s4 = new Sprite(-90,  20,TextureRegionFactory.extractFromTexture(this.movesTextureAtlas,0,375,125,125), vertexBufferObjectManager){
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if(pSceneTouchEvent.isActionDown()) {
                    atacar(MOVE_FOUR);
                }
                return true;
            }
        };

        captureSprite.setScale(0.5f);
        captureSprite.setVisible(false);
        s1.setScale(0.5f);
        s2.setScale(0.5f);
        s3.setScale(0.5f);
        s4.setScale(0.5f);
        s1.setX(-110);
        s1.setY(-100);
        s2.setX(-10);
        s2.setY(-100);
        s3.setX(-10);
        s3.setY(0);
        s4.setX(-110);
        s4.setY(0);


        commandsGroup = new Entity(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2);

        commandsGroup.attachChild(captureBox);
        commandsGroup.attachChild(captureTxt);
        commandsGroup.attachChild(captureSprite);
        commandsGroup.attachChild(moveBox1);
        commandsGroup.attachChild(moveBox2);
        commandsGroup.attachChild(moveBox3);
        commandsGroup.attachChild(moveBox4);
        commandsGroup.attachChild(move1);
        commandsGroup.attachChild(move2);
        commandsGroup.attachChild(move3);
        commandsGroup.attachChild(move4);
        commandsGroup.attachChild(s1);
        commandsGroup.attachChild(s2);
        commandsGroup.attachChild(s3);
        commandsGroup.attachChild(s4);
        scene.attachChild(commandsGroup);
        scene.registerTouchArea(captureBox);
        scene.registerTouchArea(moveBox1);
        scene.registerTouchArea(moveBox2);
        scene.registerTouchArea(moveBox3);
        scene.registerTouchArea(moveBox4);


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


        return scene;
    }


    private void atacar(int move){
        this.LeftMove = move;
        movimento();
    }
    // ===========================================================
    // Methods
    // ===========================================================

    private Rectangle makeColoredRectangle( final float pX, final float pY, final float pWidth, final float pHeight, final float pRed, final float pGreen, final float pBlue, final int move) {

        final Rectangle coloredRect = new Rectangle(pX, pY, pWidth, pHeight, this.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if(pSceneTouchEvent.isActionDown()) {
                    atacar(move);
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

    public void movimentoInicial(){
        leftRandomonSprite.animate(new long[]{400, 400}, 0, 1, true);
        rightRandomonSprite.animate(new long[]{400, 400}, 0, 1, true);

        //movesSprite.setX(leftRandomonSprite.getX());
        //movesSprite.setY(leftRandomonSprite.getY());
        //movesSprite.animate(new long[]{100,100,100,100}, 0, 3, true);
        if (loadedBattle){

            otherBattleSound = soundPoolBattle.play(soundIDBattle, 0.99f, 0.99f, priority, 100, normal_playback_rate);

        }
    }


    public void movimento() {

        //remove final if needed
        final AnimatedSprite.IAnimationListener captureAnime = new AnimatedSprite.IAnimationListener() {
            @Override
            public void onAnimationStarted(AnimatedSprite pAnimatedSprite, int pInitialLoopCount) {
            }
            @Override
            public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite, int pOldFrameIndex, int pNewFrameIndex) {
            }
            @Override
            public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite, int pRemainingLoopCount, int pInitialLoopCount) {
            }
            @Override
            public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
                movimento();
            }
        };
        final AnimatedSprite.IAnimationListener anime = new AnimatedSprite.IAnimationListener() {
            @Override
            public void onAnimationStarted(AnimatedSprite pAnimatedSprite, int pInitialLoopCount) {
            }
            @Override
            public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite, int pOldFrameIndex, int pNewFrameIndex) {
            }
            @Override
            public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite, int pRemainingLoopCount, int pInitialLoopCount) {
            }
            @Override
            public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
                movimento();
            }
        };
        if(LeftMove == 4){
            doCapture(captureAnime);

        }else{
            leftIsFirst=true;
            switch (step){
                case SPRITE_INICIO:
                    commandsGroup.setVisible(true);
                    movimentoInicial();
                    break;
                case SPRITE_AFTER_INICIO:
                    if (leftIsFirst){
                        // Left player atacks
                        leftRandomonSprite.animate(new long[]{100, 100, 100}, 8, 10, 1, anime);
                        commandsGroup.setVisible(false);
                    }else{
                        // Righs side player atacks
                        rightRandomonSprite.animate(new long[]{100, 100, 100}, 2, 4, 1, anime);
                        commandsGroup.setVisible(false);
                    }
                    break;
                case SPRITE_AFTER_ATTACK1:

                    doMove(anime);
                    break;
                case SPRITE_AFTER_MOVE1:

                    movesSprite.setVisible(false);
                    if (leftIsFirst){
                        rightRandomonSprite.animate(new long[]{100, 100}, 5, 6, 1,anime);
                    }else{
                        leftRandomonSprite.animate(new long[]{100, 100}, 11, 12,1, anime);
                    }

                    break;
                case SPRITE_AFTER_DEFENSE1:

                    if(leftIsFirst){
                        leftAtacked = true;

                    }else{
                        leftAtacked = false;
                    }
                    updateHitPoints();
                    //To change body of implemented methods use File | Settings | File Templates.
                    if(!isBattleOver){
                        leftRandomonSprite.animate(new long[]{100, 100}, 0, 1, 3,anime);
                        rightRandomonSprite.animate(new long[]{100, 100}, 0, 1, 3,anime);
                    }
                    break;
                case SPRITE_AFTER_PAUSE:
                    if (leftIsFirst){
                        // Righs side player atacks
                        rightRandomonSprite.animate(new long[]{100, 100, 100}, 2, 4, 2, anime);
                        commandsGroup.setVisible(false);
                    }else{
                        // Left player atacks
                        leftRandomonSprite.animate(new long[]{100, 100, 100}, 8, 10, 2, anime);
                        commandsGroup.setVisible(false);
                    }
                    break;
                case SPRITE_AFTER_ATTACK2:
                    doMove(anime);
                    break;
                case SPRITE_AFTER_MOVE2:
                    movesSprite.setVisible(false);
                    if (leftIsFirst){
                        leftRandomonSprite.animate(new long[]{100, 100}, 11, 12,1, anime);

                    }else{
                        rightRandomonSprite.animate(new long[]{100, 100}, 5, 6, 1,anime);
                    }

                    break;
                case SPRITE_AFTER_DEFENSE2:

                    if(leftIsFirst){
                        leftAtacked = false;
                    }else{
                        leftAtacked = true;
                    }
                    updateHitPoints();
                    break;
            }
            step++;
            if(step==SPRITE_AFTER_DEFENSE2+1){
                step=0;

                movimento();

            }
        }

    }

    private void doCapture(final AnimatedSprite.IAnimationListener captureAnime){
        //Path experiments
        int startx ,starty, endx , endy,distancex, distancey;

        startx =0;
        starty= CAMERA_HEIGHT - 150;
        endx =CAMERA_WIDTH - 130;
        endy=CAMERA_HEIGHT - 72;
        distancex = endx-startx;
        distancey = endy-starty;
        //Path path = new Path(5).to(startx, starty).to(startx+distancex/4,starty).to(startx+distancex/4*2,starty).to(startx+distancex/4*3,starty).to(endx, endy);
        RoundedPath pathC = new RoundedPath();

        Path path = pathC.path(startx, starty-30, startx+distancex/4,starty-80, endx-15, starty-80, endx+5, endy+5);
        rightRandomonSprite.setVisible(false);

        final int catched;
        catched = (int)Math.floor(Math.random()*5);

        /*Path path = new Path(9).to(startx, starty).to(startx+distancex/8,starty-15).to(startx+distancex/8*2,starty-30).to(startx+distancex/8*3,starty+45)
                .to(startx+distancex/8*4,starty+60).to(startx+distancex/8*5,starty+75).to(startx+distancex/8*6,starty+90).to(startx+distancex/8*7,starty+105).to(endx, endy);
*/

        captSprite.setVisible(true);
        captSprite.registerEntityModifier(new SequenceEntityModifier(new PathModifier(0.4f, path, null, new IPathModifierListener() {
            @Override
            public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) {
                commandsGroup.setVisible(false);
                captSprite.animate(new long[]{100},new int[]{0},true);
            }
            @Override
            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {

                //movesSprite.animate(new long[]{100},new int[]{frameIndex},true);
                //frameIndex++;
                //To change body of implemented methods use File | Settings | File Templates.
            }
            @Override
            public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {

            }
            @Override
            public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) {
                if (catched <= 2){
                    //FAILED CAPTURE
                    rightRandomonSprite.setVisible(true);
                    captSprite.setVisible(false);
                    commandsGroup.setVisible(true);
                }else{
                    catchSuccess();



                    shared.getPlayer().getRandomonCollection().add(rightRandomon);
                }


            }
        }, EaseSineInOut.getInstance())));

    }

    private void doMove(final AnimatedSprite.IAnimationListener anime) {
        //Path experiments
        int startx ,starty, endx , endy,distancex;
        final int movenr;

        if(leftAtacked){
            endx =90;
            endy= CAMERA_HEIGHT - 72;
            startx =CAMERA_WIDTH - 130;
            starty=CAMERA_HEIGHT - 72;
            distancex = endx-startx;
            movenr= (int)Math.floor(Math.random()*4);
        }else{
            movenr= this.LeftMove;
            startx =90;
            starty= CAMERA_HEIGHT - 72;
            endx =CAMERA_WIDTH - 130;
            endy=CAMERA_HEIGHT - 72;
            distancex = endx-startx;
        }

        Path path = new Path(5).to(startx, starty).to(startx+distancex/4,starty).to(startx+distancex/4*2,starty).to(startx+distancex/4*3,starty).to(endx, endy);
        movesSprite.setVisible(true);
        movesSprite.registerEntityModifier(new SequenceEntityModifier(new PathModifier(0.2f, path, null, new IPathModifierListener() {
            int frameIndex= movenr*4;
            @Override
            public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) {
                frameIndex=0;
                movesSprite.animate(new long[]{100},new int[]{frameIndex},true);
            }
            @Override
            public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
                switch (movenr){
                    case 0:

                        if (loadedWild==true){

                            soundPoolWild.play(soundIDWild, 0.99f, 0.99f, priority, 0, normal_playback_rate);

                        }
                        movesSprite.animate(new long[]{100},new int[]{frameIndex+0},true);
                        frameIndex++;
                        break;
                    case 1:
                        if (loadedFire==true){

                            soundPoolFire.play(soundIDFire, 0.99f, 0.99f, priority, 0, normal_playback_rate);

                        }

                        movesSprite.animate(new long[]{100},new int[]{frameIndex+4},true);
                        frameIndex++;
                        break;
                    case 2:
                        if (loadedFlying==true){

                            soundPoolFlying.play(soundIDFlying, 0.99f, 0.99f, priority, 0, normal_playback_rate);

                        }

                        movesSprite.animate(new long[]{100},new int[]{frameIndex+8},true);
                        frameIndex++;
                        break;
                    case 3:

                        if (loadedFatal==true){

                            soundPoolFatal.play(soundIDFatal, 0.99f, 0.99f, priority, 0, normal_playback_rate);

                        }

                        movesSprite.animate(new long[]{100},new int[]{frameIndex+12},true);
                        frameIndex++;
                        break;
                }

                //movesSprite.animate(new long[]{100},new int[]{frameIndex},true);
                //frameIndex++;
                //To change body of implemented methods use File | Settings | File Templates.
            }
            @Override
            public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {

            }
            @Override
            public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) {
                movesSprite.animate(new long[]{100},new int[]{frameIndex},1,anime);
            }
        }, EaseSineInOut.getInstance())));

    }

    private void updateHitPoints(){
        if(leftAtacked){
            rightRandomon.setCurrent_hitpoints(rightRandomon.getCurrent_hitpoints()-leftRandomon.getAttack());
        }else{
            leftRandomon.setCurrent_hitpoints(leftRandomon.getCurrent_hitpoints()-rightRandomon.getAttack());
        }
        drawHitpoints();
        updateText();

    }

    public boolean onKeyDown(int keyCode,KeyEvent event){

        if (keyCode == KeyEvent.KEYCODE_BACK){
            System.out.println("key pressed");
            return false;
        }
        return false;

    }

    private void drawHitpoints(){
        this.isBattleOver=false;
        if (leftRandomon.getCurrent_hitpoints()>0){
            leftRandomonHitPoints.setWidth(leftRandomon.getCurrent_hitpoints()*150/leftRandomon.getHitpoints());
        }
        else{
            leftRandomonHitPoints.setVisible(false);
            youLost();
            this.isBattleOver=true;


            return;
        }
        if (rightRandomon.getCurrent_hitpoints()>0)
            rightRandomonHitPoints.setWidth(rightRandomon.getCurrent_hitpoints()*150/rightRandomon.getHitpoints());
        else{
            rightRandomonHitPoints.setVisible(false);
            youWin();
            this.isBattleOver=true;
            return;
        }

    }

    private void youWin(){

        leftRandomonSprite.animate(new long[]{400, 400}, 0, 1, 5,animeEnd);
        rightRandomonSprite.animate(new long[]{100, 100, 100}, 5, 7, 1);
        commandsGroup.setVisible(false);
        scene.unregisterTouchArea(moveBox1);
        scene.unregisterTouchArea(moveBox2);
        scene.unregisterTouchArea(moveBox3);
        scene.unregisterTouchArea(moveBox4);

        resultText.setText("You Win!!!");
        resultText.setX(CAMERA_WIDTH / 2 - resultText.getWidth() / 2);
        resultText.setY(CAMERA_HEIGHT/2-resultText.getHeight()/2);
        resultText.setVisible(true);

    }

    private void youLost(){
        rightRandomonSprite.animate(new long[]{400, 400}, 0, 1, 5, animeEnd);
        leftRandomonSprite.animate(new long[]{100, 100, 100}, 11, 13, 1);
        commandsGroup.setVisible(false);
        scene.unregisterTouchArea(moveBox1);
        scene.unregisterTouchArea(moveBox2);
        scene.unregisterTouchArea(moveBox3);
        scene.unregisterTouchArea(moveBox4);

        resultText.setText("You Lose!!!");
        resultText.setX(CAMERA_WIDTH / 2 - resultText.getWidth() / 2);
        resultText.setY(CAMERA_HEIGHT / 2 - resultText.getHeight() / 2);
        resultText.setVisible(true);


    }

    private void catchSuccess(){
        leftRandomonSprite.animate(new long[]{400, 400}, 0, 1, 5,animeEnd);
        rightRandomonSprite.animate(new long[]{100, 100, 100}, 5, 7, 1);
        commandsGroup.setVisible(false);
        scene.unregisterTouchArea(moveBox1);
        scene.unregisterTouchArea(moveBox2);
        scene.unregisterTouchArea(moveBox3);
        scene.unregisterTouchArea(moveBox4);

        resultText.setText("Nice Catch!");
        resultText.setX(CAMERA_WIDTH / 2 - resultText.getWidth() / 2);
        resultText.setY(CAMERA_HEIGHT/2-resultText.getHeight()/2);
        resultText.setVisible(true);
    }

    private void updateText(){
        String text1 = leftRandomon.getName()+" lvl."+leftRandomon.getLevel()+" "+" ("+leftRandomon.getCurrent_hitpoints()+"/"+leftRandomon.getHitpoints()+")";
        String text2 = rightRandomon.getName()+" lvl."+rightRandomon.getLevel()+" "+" ("+rightRandomon.getCurrent_hitpoints()+"/"+rightRandomon.getHitpoints()+")";
        leftRandomonText.setText(text1);
        rightRandomonText.setText(text2);
    }


    final AnimatedSprite.IAnimationListener animeEnd = new AnimatedSprite.IAnimationListener() {
        @Override
        public void onAnimationStarted(AnimatedSprite pAnimatedSprite, int pInitialLoopCount) {
        }
        @Override
        public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite, int pOldFrameIndex, int pNewFrameIndex) {
        }
        @Override
        public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite, int pRemainingLoopCount, int pInitialLoopCount) {
        }
        @Override
        public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {

            soundPoolBattle.stop(otherBattleSound);
            soundPoolBattle.release();


            finish();
        }
    };
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
