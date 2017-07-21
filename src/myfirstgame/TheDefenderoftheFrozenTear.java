package myfirstgame;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.animation.SkeletonControl;
import com.jme3.bullet.BulletAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import java.awt.image.BufferedImage;
import com.jme3.input.ChaseCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.MotionPathListener;
import com.jme3.cinematic.events.MotionEvent;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.effect.shapes.EmitterSphereShape;
import com.jme3.font.BitmapText;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import com.jme3.light.SpotLight;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.ui.Picture;
import com.jme3.util.SkyFactory;
import com.jme3.util.TangentBinormalGenerator;
import java.io.File;
import java.io.IOException;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class TheDefenderoftheFrozenTear extends SimpleApplication implements ActionListener, AnimEventListener {

    private BulletAppState bulletAppState;
    private RigidBodyControl landscape;
    //character
    BitmapText axemsg;
    BitmapText swordmsg;
    BitmapText molotovmsg;
    BitmapText sinhp;
    BitmapText househp;
    BitmapText npc1hp;
    BitmapText npc2hp;
    BitmapText scoretxt;
    BitmapText repmsg;
    int score = 0;
    CharacterControl character, sun, npc1, npc2;
    SpotLight spot, spot2;
    Geometry lightMdl, nightMdl, portal1, portal2;
    Spatial modelhouse, log;
    int sinHealth = 1000;
    int houseHealth = 1000000;
    int npc1Health = 100;
    int npc2Health = 100;
    int wincounter = 0;
    int losscounter = 0;
    int kill1counter = 0;
    int kill2counter = 0;
    Boolean meleehitnpc1 = false;
    Boolean meleehitnpc2 = false;
    Boolean molohitnpc1 = false;
    Boolean molohitnpc2 = false;
    Boolean axepressed = false;
    Boolean swordpressed = false;
    Boolean molotovpressed = false;
    Boolean reppressed = false;
    Boolean safezone = false;
    Boolean rangedhit = false;
    Boolean swordhit = false;
    Boolean oto1hithouse = false;
    Boolean oto1hitsin = false;
    Boolean oto2hithouse = false;
    Boolean oto2hitsin = false;
    Boolean win = false;
    Boolean loss = false;
    Boolean killmob1 = false;
    Boolean killmob2 = false;
    float interactrange = 12;
    int axemsgtimer = 0;
    int swordmsgtimer = 0;
    int molotovmsgtimer = 0;
    int repmsgtimer = 0;
    Node model, model2, modelNodes, tree, modelmolotov, modelNPC, modelNPC2, modelBoss, modelweap, sinhpport, npc1hpport, npc2hpport, scoreport, househpport, moloinv, logsinv;
    Node cut, pick, shootables, molo, ax, blade, textNode, moloth, molo2, molo3, molo4, notifications, mob1, mob2, weaprightport, weapleftport;
    float displacement = 60;
    Geometry mark;
    Geometry molotov1;
    Vector3f walkDirection = new Vector3f();
    Vector3f walkNPC = new Vector3f();
    Vector3f walkNPC2 = new Vector3f();
    Vector3f sinBadLocation = new Vector3f();
    Vector3f characterNPCLocation = new Vector3f();
    Vector3f characterNPC2Location = new Vector3f();
    float aggrorange = 20;
    Vector3f portalloc1 = new Vector3f(-197, 22, 97);
    Vector3f portalloc2 = new Vector3f(-204, 14, -201);
    Vector3f house = new Vector3f(154, 23, 154);
    Vector3f waypointa1 = new Vector3f(-168, 20, 104);
    Vector3f waypointa2 = new Vector3f(-88, 20, 109);
    Vector3f waypointa3 = new Vector3f(-88, 19, 41);
    Vector3f waypointa4 = new Vector3f(36, 20, 45);
    Vector3f waypointa5 = new Vector3f(213, 6, 40);
    Boolean pointa1 = true;
    Boolean pointa2 = false;
    Boolean pointa3 = false;
    Boolean pointa4 = false;
    Boolean pointa5 = false;
    Boolean cubea = false;
    Vector3f waypointb1 = new Vector3f(-148, 10, -185);
    Vector3f waypointb2 = new Vector3f(-63, 5.5f, -162);
    Vector3f waypointb3 = new Vector3f(17, 8, -142);
    Vector3f waypointb4 = new Vector3f(80, 14, -146);
    Vector3f waypointb5 = new Vector3f(195, 8, -126);
    Vector3f waypointb6 = new Vector3f(208, 6, 65);
    Boolean pointb1 = true;
    Boolean pointb2 = false;
    Boolean pointb3 = false;
    Boolean pointb4 = false;
    Boolean pointb5 = false;
    Boolean pointb6 = false;
    Boolean cubeb = false;
    Boolean housea = false;
    Boolean houseb = false;
    Boolean playactivated = false;
    RigidBodyControl terrainPhysicsNode;
    PointLight molotov_light, explosion_light;
    //animation
    AnimChannel animationChannel, animationChannel2, animationChannelNPC, animationChannelNPC2, animationChannelBoss;
    AnimChannel shootingChannel;
    AnimControl animationControl, animationControlNPC, animationControlNPC2, animationControlBoss;
    float airTime = 0;
    /*private MotionPath path, path2;
     private MotionEvent motionControl, motionControl2;*/
    //camera
    boolean left = false, right = false, up = false, down = false;
    ChaseCamera chaseCam;
    private Vector3f lightTarget = new Vector3f(12, 3.5f, 30);
    private Spatial sceneModel;
    FilterPostProcessor fpp;
    int nlogs = 0;
    int nmolo = 0;
    int time2;
    Spatial molotov, molotov2, molotov3, molotov4, sword, sword2, axe, axe2;
    Boolean swordeq = false;
    Boolean axeeq = false;
    Boolean molotoveq = false;
    Boolean swordloot = false;
    Boolean axeloot = false;
    Boolean molotovloot = false;
    Boolean molotovhit = false;
    Boolean righteq = false;
    Boolean lefteq = false;
    Boolean thrown = false;
    Boolean explosion = false;
    Boolean onair = false;
    Boolean usedmolo2 = false;
    Boolean usedmolo3 = false;
    Boolean usedmolo4 = false;
    Boolean day = false;
    Boolean kill1 = false;
    Boolean kill2 = false;
    private AudioNode canttouch;
    Node skmol, skaxe, skswd;
    private Node explosionEffect = new Node("explosionFX");
    Node flameEffect, starthud;
    private float time = 0;
    private int state = 0;
    CollisionShape moloshape;
    Picture victorypic, pic, losspic, startpic;
    private RigidBodyControl mol_phy;
    private RigidBodyControl portal1_phy, portal2_phy;
    private ParticleEmitter fire, flame, flash, spark, roundspark, smoketrail, debris, shockwave;
    private static final int COUNT_FACTOR = 1;
    private static final float COUNT_FACTOR_F = 1f;
    private static final boolean POINT_SPRITE = true;
    private static final ParticleMesh.Type EMITTER_TYPE = POINT_SPRITE ? ParticleMesh.Type.Point : ParticleMesh.Type.Triangle;

    public static void main(String[] args) {
        TheDefenderoftheFrozenTear app = new TheDefenderoftheFrozenTear();
        AppSettings cfg = new AppSettings(true);
        cfg.setFrameRate(60);
        cfg.setVSync(true);
        cfg.setFrequency(60);
        cfg.setResolution(1280, 720);
        cfg.setSamples(8);
        cfg.setTitle("The Defender of the Frozen Tear");
        try {
            cfg.setIcons(new BufferedImage[]{ImageIO.read(new File("assets/Interface/icon.gif"))});
        } catch (IOException ex) {
            Logger.getLogger(TheDefenderoftheFrozenTear.class.getName()).log(Level.SEVERE, "Icon missing.", ex);
        }
        cfg.setSettingsDialogImage("Interface/sinbad_ogremascot.jpg");
        app.setSettings(cfg);
        app.start();
    }
    private Random rand = new Random();

    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        shootables = new Node("Shootables");
        rootNode.attachChild(shootables);
        cut = new Node("cut");
        rootNode.attachChild(cut);
        pick = new Node("pick");
        rootNode.attachChild(pick);
        molo = new Node("molo");
        rootNode.attachChild(molo);
        molo2 = new Node("molo");
        rootNode.attachChild(molo2);
        molo3 = new Node("molo");
        rootNode.attachChild(molo3);
        molo4 = new Node("molo");
        rootNode.attachChild(molo4);
        textNode = new Node("text");
        guiNode.attachChild(textNode);
        notifications = new Node("notifications");
        guiNode.attachChild(notifications);
        ax = new Node("ax");
        rootNode.attachChild(ax);
        blade = new Node("blade");
        rootNode.attachChild(blade);
        moloth = new Node("molo");
        rootNode.attachChild(moloth);
        mob1 = new Node("mob1");
        shootables.attachChild(mob1);
        mob2 = new Node("mob2");
        shootables.attachChild(mob2);
        weaprightport = new Node("text");
        guiNode.attachChild(weaprightport);
        weapleftport = new Node("text");
        guiNode.attachChild(weapleftport);
        sinhpport = new Node("text");
        guiNode.attachChild(sinhpport);
        npc1hpport = new Node("text");
        guiNode.attachChild(npc1hpport);
        npc2hpport = new Node("text");
        guiNode.attachChild(npc2hpport);
        scoreport = new Node("text");
        guiNode.attachChild(scoreport);
        househpport = new Node("text");
        guiNode.attachChild(househpport);
        moloinv = new Node("text");
        guiNode.attachChild(moloinv);
        logsinv = new Node("text");
        guiNode.attachChild(logsinv);
        starthud = new Node("starthud");
        guiNode.attachChild(starthud);

        explosionEffect.setLocalScale(0.5f);
        renderManager.preloadScene(explosionEffect);

        rootNode.attachChild(explosionEffect);

        setupKeys();
        createLight();
        createSun();
        createMoon();
        createSky();
        createTerrain2();
        createCharacter();
        setupChaseCamera();
        setupAnimationController();
        createHouse();
        createCharacterNPC();
        createCharacterNPC2();
        createBoom();
        createFire();
        createHud();
        makePortalBlue();
        makePortalYellow();
        createAxeEq();
        createSwordEq();
        createDefeatHud();
        createVictoryHud();
        createStartingHud();
        //setupMotionPath();
        createMolotov();
        createSword();
        initAudio();
        createAxe();
        createTree();
        FilterPostProcessor fx;
        fx = assetManager.loadFilter("Materials/water.j3f");
        viewPort.addProcessor(fx);
        initCrossHairs();
        initMark();
        getAttachmentsNodemolo();
        getAttachmentsNodeaxe();
        getAttachmentsNodesword();


        //bulletAppState.getPhysicsSpace().enableDebug(assetManager);
    }

    private PhysicsSpace getPhysicsSpace() {
        return bulletAppState.getPhysicsSpace();
    }

    private void createHud() {
        pic = new Picture("HUD Picture");
        pic.setImage(assetManager, "Textures/oeo 2.png", true);
        pic.setWidth(settings.getWidth());
        pic.setHeight(settings.getHeight());
        pic.setPosition(0, 0);
        guiNode.attachChild(pic);
    }

    private void initAudio() {
        /* nature sound - keeps playing in a loop. */
        canttouch = new AudioNode(assetManager, "Sound/You can't touch this - MC Hammer - Lyrics(1).wav", true);
        //canttouch.setLooping(true);  // activate continuous playing
        canttouch.setPositional(false);
        canttouch.setVolume(1);
        rootNode.attachChild(canttouch);
        canttouch.stop(); // play continuously!
    }

    private void createDefeatHud() {
        losspic = new Picture("HUD Picture");
        losspic.setImage(assetManager, "Textures/Loss Baner.png", true);
        losspic.setWidth(settings.getWidth());
        losspic.setHeight(settings.getHeight());
        losspic.setPosition(0, 0);
    }

    private void createStartingHud() {
        startpic = new Picture("HUD Picture");
        startpic.setImage(assetManager, "Textures/SKULL.png", true);
        startpic.setWidth(settings.getWidth());
        startpic.setHeight(settings.getHeight());
        startpic.setPosition(0, 0);
    }

    private void createVictoryHud() {
        victorypic = new Picture("HUD Picture");
        victorypic.setImage(assetManager, "Textures/Victory Banner.png", true);
        victorypic.setWidth(settings.getWidth());
        victorypic.setHeight(settings.getHeight());
        victorypic.setPosition(0, 0);
    }

    private void setupKeys() {
        inputManager.addMapping("wireframe", new KeyTrigger(KeyInput.KEY_T));
        inputManager.addListener(this, "wireframe");
        inputManager.addMapping("CharLeft", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("CharRight", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("CharUp", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("CharDown", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("CharSpace", new KeyTrigger(KeyInput.KEY_SPACE));
        //inputManager.addMapping("Attack",new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("Melee", new KeyTrigger(KeyInput.KEY_H));
        inputManager.addMapping("EquipSword", new KeyTrigger(KeyInput.KEY_Z));
        inputManager.addMapping("EquipAxe", new KeyTrigger(KeyInput.KEY_X));
        inputManager.addMapping("EquipMolotov", new KeyTrigger(KeyInput.KEY_C));
        inputManager.addMapping("UnequipRight", new KeyTrigger(KeyInput.KEY_E));
        inputManager.addMapping("UnequipLeft", new KeyTrigger(KeyInput.KEY_Q));
        inputManager.addMapping("Throw", new KeyTrigger(KeyInput.KEY_F));
        inputManager.addMapping("Repair", new KeyTrigger(KeyInput.KEY_R));
        inputManager.addMapping("Play", new KeyTrigger(KeyInput.KEY_P));
        inputManager.addMapping("Attack", new KeyTrigger(KeyInput.KEY_G));

        inputManager.addListener(this, "CharLeft");
        inputManager.addListener(this, "CharRight");
        inputManager.addListener(this, "CharUp");
        inputManager.addListener(this, "CharDown");
        inputManager.addListener(this, "CharSpace");
        inputManager.addListener(this, "Melee");
        inputManager.addListener(this, "EquipSword");
        inputManager.addListener(this, "EquipAxe");
        inputManager.addListener(this, "EquipMolotov");
        inputManager.addListener(this, "UnequipRight");
        inputManager.addListener(this, "UnequipLeft");
        inputManager.addListener(this, "Throw");
        inputManager.addListener(this, "Repair");
        inputManager.addListener(this, "Play");
        inputManager.addListener(this, "Attack");
    }

    private void createLight() {
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.15f));
        rootNode.addLight(al);

        spot = new SpotLight();
        spot.setSpotRange(1000);
        spot.setSpotInnerAngle(25 * FastMath.DEG_TO_RAD);
        spot.setSpotOuterAngle(50 * FastMath.DEG_TO_RAD);
        spot.setPosition(new Vector3f(77.70334f, 34.013165f, 27.1017f));
        spot.setDirection(lightTarget.subtract(spot.getPosition()));
        spot.setColor(ColorRGBA.White.mult(2));
        //spot.setColor(new ColorRGBA(77, 197, 214, 1f));
        rootNode.addLight(spot);

        spot2 = new SpotLight();
        spot2.setSpotRange(1000);
        spot2.setSpotInnerAngle(25 * FastMath.DEG_TO_RAD);
        spot2.setSpotOuterAngle(50 * FastMath.DEG_TO_RAD);
        spot2.setPosition(new Vector3f(77.70334f, -34.013165f, 27.1017f));
        spot2.setDirection(lightTarget.subtract(spot2.getPosition()));
        spot2.setColor(ColorRGBA.White.mult(0.25f));
        //spot2.setColor(new ColorRGBA(188, 221, 17, 1).mult(0.25f));
        rootNode.addLight(spot2);

        molotov_light = new PointLight();
        molotov_light.setColor(ColorRGBA.Yellow);
        molotov_light.setRadius(7f);
        moloth.addLight(molotov_light);

        explosion_light = new PointLight();
        explosion_light.setColor(ColorRGBA.Orange);
        explosion_light.setRadius(0.0000000000001f);
        rootNode.addLight(explosion_light);
    }

    private void createCharacterNPC() {
        CapsuleCollisionShape capsule = new CapsuleCollisionShape(1.5f, 3.3f, 1);
        modelNPC = (Node) assetManager.loadModel("Models/Oto/Oto.j3o");
        modelNPC.setLocalScale(0.7f);
        mob1.attachChild(modelNPC);
        animationControlNPC = modelNPC.getControl(AnimControl.class);
        animationControlNPC.addListener(this);
        animationChannelNPC = animationControlNPC.createChannel();
        animationChannelNPC.setAnim("stand");
        npc1 = new CharacterControl(capsule, 2.7f);
        npc1.setGravity(30f);
        npc1.setFallSpeed(30f);
        npc1.setMaxSlope(0.7f);
        modelNPC.setUserData("npc1Health", npc1Health);
        modelNPC.addControl(npc1);
        npc1.setPhysicsLocation(new Vector3f(-201, 19, 61));
        getPhysicsSpace().add(npc1);
    }

    private void createCharacterNPC2() {
        CapsuleCollisionShape capsule = new CapsuleCollisionShape(1.5f, 3.3f, 1);
        npc2 = new CharacterControl(capsule, 4.0f);
        modelNPC2 = (Node) assetManager.loadModel("Models/Oto/Oto.j3o");
        modelNPC2.setLocalScale(0.7f);
        npc2.setJumpSpeed(30f);
        npc2.setGravity(30f);
        npc2.setFallSpeed(30f);
        npc2.setMaxSlope(0.7f);
        modelNPC2.addControl(npc2);
        modelNPC2.setUserData("npc2Health", npc2Health);
        npc2.setPhysicsLocation(new Vector3f(-201, 11, -183));
        animationControlNPC2 = modelNPC2.getControl(AnimControl.class);
        animationChannelNPC2 = animationControlNPC2.createChannel();
        animationChannelNPC2.setAnim("stand");

        mob2.attachChild(modelNPC2);
        getPhysicsSpace().add(npc2);
    }

    private void setupMotionPatha() {
        characterNPCLocation = npc1.getPhysicsLocation();
        sinBadLocation = character.getPhysicsLocation();
        if (day == true) {
            if (cubea == true && characterNPCLocation.distance(sinBadLocation) > aggrorange) {
                walkNPC = waypointa1.subtract(characterNPCLocation).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc1.setViewDirection(walkNPC);
                if (characterNPCLocation.distance(waypointa1) < 1) {
                    pointa2 = true;
                    cubea = false;
                }
            } else if (pointa1 == true && characterNPCLocation.distance(sinBadLocation) > aggrorange) {
                walkNPC = waypointa1.subtract(characterNPCLocation).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc1.setViewDirection(walkNPC);
                if (characterNPCLocation.distance(waypointa1) < 1) {
                    pointa2 = true;
                    pointa1 = false;
                }
            } else if (pointa2 == true && characterNPCLocation.distance(sinBadLocation) > aggrorange) {
                walkNPC = waypointa2.subtract(characterNPCLocation).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc1.setViewDirection(walkNPC);
                if (characterNPCLocation.distance(waypointa2) < 1) {
                    pointa3 = true;
                    pointa2 = false;
                }
            } else if (pointa3 == true && characterNPCLocation.distance(sinBadLocation) > aggrorange) {
                walkNPC = waypointa3.subtract(characterNPCLocation).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc1.setViewDirection(walkNPC);
                if (characterNPCLocation.distance(waypointa3) < 1) {
                    pointa4 = true;
                    pointa3 = false;
                }
            } else if (pointa4 == true && characterNPCLocation.distance(sinBadLocation) > aggrorange) {
                walkNPC = waypointa4.subtract(characterNPCLocation).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc1.setViewDirection(walkNPC);
                if (characterNPCLocation.distance(waypointa4) < 1) {
                    pointa5 = true;
                    pointa4 = false;
                }
            } else if (pointa5 == true && characterNPCLocation.distance(sinBadLocation) > aggrorange) {
                walkNPC = waypointa5.subtract(characterNPCLocation).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc1.setViewDirection(walkNPC);
                if (characterNPCLocation.distance(waypointa5) < 1) {
                    housea = true;
                    pointa5 = false;
                }
            } else if (housea == true && characterNPCLocation.distance(sinBadLocation) > aggrorange && !modelhouse.getWorldBound().intersects(modelNPC.getWorldBound())) {
                walkNPC = house.subtract(characterNPCLocation).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc1.setViewDirection(walkNPC);
            } else if (characterNPCLocation.distance(sinBadLocation) <= aggrorange && !model.getWorldBound().intersects(modelNPC.getWorldBound()) && safezone == false) {
                walkNPC = sinBadLocation.subtract(characterNPCLocation).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc1.setViewDirection(walkNPC);
            } else if (model.getWorldBound().intersects(modelNPC.getWorldBound()) && safezone == false && killmob1 == false && loss == false) {
                walkNPC = sinBadLocation.subtract(characterNPCLocation).normalize().multLocal(0.0001f);
                npc1.setViewDirection(walkNPC);
                //npc1.setWalkDirection(walkNPC);
                if (!"Dodge".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Dodge", 0.7f);
                    animationChannelNPC.setSpeed(0.5f);
                } else if ("Dodge".equals(animationChannelNPC.getAnimationName())) {
                    oto1hitsin = true;
                }
            } else if (housea == true && modelhouse.getWorldBound().intersects(modelNPC.getWorldBound())) {
                walkNPC = house.subtract(characterNPCLocation).normalize().multLocal(0.000001f);
                npc1.setViewDirection(walkNPC);
                //npc1.setWalkDirection(walkNPC);
                if (!"pull".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("pull", 0.7f);
                    animationChannelNPC.setSpeed(0.5f);
                }
                if ("pull".equals(animationChannelNPC.getAnimationName())) {
                    oto1hithouse = true;
                }
            } else if (loss == true) {
                if (!"stand".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("stand", 0.7f);
                }
            }
        } else if (day == false) {
            if (housea == true && characterNPCLocation.distance(sinBadLocation) > aggrorange) {
                walkNPC = waypointa5.subtract(characterNPCLocation).normalize().multLocal(0.4f);
                if (!"Walk".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc1.setViewDirection(walkNPC);
                if (characterNPCLocation.distance(waypointa5) < 1) {
                    pointa5 = true;
                    housea = false;
                }
            } else if (pointa5 == true && characterNPCLocation.distance(sinBadLocation) > aggrorange) {
                walkNPC = waypointa4.subtract(characterNPCLocation).normalize().multLocal(0.4f);
                if (!"Walk".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc1.setViewDirection(walkNPC);
                if (characterNPCLocation.distance(waypointa4) < 1) {
                    pointa4 = true;
                    pointa5 = false;
                }
            } else if (pointa4 == true && characterNPCLocation.distance(sinBadLocation) > aggrorange) {
                walkNPC = waypointa3.subtract(characterNPCLocation).normalize().multLocal(0.4f);
                if (!"Walk".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc1.setViewDirection(walkNPC);
                if (characterNPCLocation.distance(waypointa3) < 1) {
                    pointa3 = true;
                    pointa4 = false;
                }
            } else if (pointa3 == true && characterNPCLocation.distance(sinBadLocation) > aggrorange) {
                walkNPC = waypointa2.subtract(characterNPCLocation).normalize().multLocal(0.4f);
                if (!"Walk".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc1.setViewDirection(walkNPC);
                if (characterNPCLocation.distance(waypointa2) < 1) {
                    pointa2 = true;
                    pointa3 = false;
                }
            } else if (pointa2 == true && characterNPCLocation.distance(sinBadLocation) > aggrorange) {
                walkNPC = waypointa1.subtract(characterNPCLocation).normalize().multLocal(0.4f);
                if (!"Walk".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc1.setViewDirection(walkNPC);
                if (characterNPCLocation.distance(waypointa1) < 1) {
                    pointa1 = true;
                    pointa2 = false;
                }
            } else if (model.getWorldBound().intersects(modelNPC.getWorldBound()) && safezone == false && killmob1 == false && loss == false) {
                walkNPC = sinBadLocation.subtract(characterNPCLocation).normalize().multLocal(0.0001f);
                npc1.setViewDirection(walkNPC);
                if (!"Dodge".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Dodge", 0.7f);
                    animationChannelNPC.setSpeed(0.5f);
                } else if ("Dodge".equals(animationChannelNPC.getAnimationName())) {
                    oto1hitsin = true;
                }
            } else if (pointa1 == true && characterNPCLocation.distance(sinBadLocation) > aggrorange) {
                walkNPC = portalloc1.subtract(characterNPCLocation).normalize().multLocal(0.4f);
                if (!"Walk".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc1.setViewDirection(walkNPC);
                cubea = true;
                pointa1 = false;
            } else if (cubea == true && portal1.getWorldBound().intersects(modelNPC.getWorldBound()) && characterNPCLocation.distance(sinBadLocation) > aggrorange) {
                walkNPC = portalloc1.subtract(characterNPCLocation).normalize().multLocal(0.0000001f);
                npc1.setViewDirection(walkNPC);
                if (!"push".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("push", 0.7f);
                    animationChannelNPC.setLoopMode(LoopMode.DontLoop);
                }
            } else if (loss == true) {
                if (!"stand".equals(animationChannelNPC.getAnimationName())) {
                    animationChannelNPC.setAnim("stand", 0.7f);
                }
            }
        }
    }

    private void setupMotionPathb() {
        characterNPC2Location = npc2.getPhysicsLocation();
        sinBadLocation = character.getPhysicsLocation();
        if (day == true) {
            if (cubeb == true && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = waypointb1.subtract(characterNPC2Location).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
                if (characterNPC2Location.distance(waypointb1) < 1) {
                    pointb2 = true;
                    cubeb = false;
                }
            }
            if (pointb1 == true && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = waypointb1.subtract(characterNPC2Location).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
                if (characterNPC2Location.distance(waypointb1) < 1) {
                    pointb2 = true;
                    pointb1 = false;
                }
            } else if (pointb2 == true && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = waypointb2.subtract(characterNPC2Location).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
                if (characterNPC2Location.distance(waypointb2) < 1) {
                    pointb3 = true;
                    pointb2 = false;
                }
            } else if (pointb3 == true && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = waypointb3.subtract(characterNPC2Location).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
                if (characterNPC2Location.distance(waypointb3) < 1) {
                    pointb4 = true;
                    pointb3 = false;
                }
            } else if (pointb4 == true && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = waypointb4.subtract(characterNPC2Location).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
                if (characterNPC2Location.distance(waypointb4) < 1) {
                    pointb5 = true;
                    pointb4 = false;
                }
            } else if (pointb5 == true && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = waypointb5.subtract(characterNPC2Location).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
                if (characterNPC2Location.distance(waypointb5) < 1) {
                    pointb6 = true;
                    pointb5 = false;
                }
            } else if (pointb6 == true && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = waypointb6.subtract(characterNPC2Location).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
                if (characterNPC2Location.distance(waypointb6) < 1) {
                    houseb = true;
                    pointb6 = false;
                }
            } else if (houseb == true && characterNPC2Location.distance(sinBadLocation) > aggrorange && !modelhouse.getWorldBound().intersects(modelNPC2.getWorldBound())) {
                walkNPC2 = house.subtract(characterNPC2Location).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
            } else if (characterNPC2Location.distance(sinBadLocation) <= aggrorange && !model.getWorldBound().intersects(modelNPC2.getWorldBound()) && safezone == false) {
                walkNPC2 = sinBadLocation.subtract(characterNPC2Location).normalize().multLocal(0.45f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
            } else if (model.getWorldBound().intersects(modelNPC2.getWorldBound()) && safezone == false && killmob2 == false && loss == false) {
                walkNPC2 = sinBadLocation.subtract(characterNPC2Location).normalize().multLocal(0.0001f);
                npc2.setViewDirection(walkNPC2);
                if (!"Dodge".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Dodge", 0.7f);
                    animationChannelNPC2.setSpeed(0.5f);
                } else if ("Dodge".equals(animationChannelNPC2.getAnimationName())) {
                    oto2hitsin = true;
                }
            } else if (houseb == true && modelhouse.getWorldBound().intersects(modelNPC2.getWorldBound())) {
                walkNPC2 = house.subtract(characterNPC2Location).normalize().multLocal(0.000001f);
                npc2.setViewDirection(walkNPC2);
                if (!"pull".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("pull", 0.7f);
                    animationChannelNPC2.setSpeed(0.5f);
                }
                if ("pull".equals(animationChannelNPC2.getAnimationName())) {
                    oto2hithouse = true;
                }
            } else if (loss == true) {
                if (!"stand".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("stand", 0.7f);
                }
            }
        } else if (day == false) {
            if (houseb == true && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = waypointb6.subtract(characterNPC2Location).normalize().multLocal(0.4f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
                if (characterNPC2Location.distance(waypointb6) < 1) {
                    pointb6 = true;
                    houseb = false;
                }
            } else if (pointb6 == true && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = waypointb5.subtract(characterNPC2Location).normalize().multLocal(0.4f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
                if (characterNPC2Location.distance(waypointb5) < 1) {
                    pointb5 = true;
                    pointb6 = false;
                }
            } else if (pointb5 == true && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = waypointb4.subtract(characterNPC2Location).normalize().multLocal(0.4f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
                if (characterNPC2Location.distance(waypointb4) < 1) {
                    pointb4 = true;
                    pointb5 = false;
                }
            } else if (pointb4 == true && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = waypointb3.subtract(characterNPC2Location).normalize().multLocal(0.4f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
                if (characterNPC2Location.distance(waypointb3) < 1) {
                    pointb3 = true;
                    pointb4 = false;
                }
            } else if (pointb3 == true && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = waypointb2.subtract(characterNPC2Location).normalize().multLocal(0.4f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
                if (characterNPC2Location.distance(waypointb2) < 1) {
                    pointb2 = true;
                    pointb3 = false;
                }
            } else if (pointb2 == true && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = waypointb1.subtract(characterNPC2Location).normalize().multLocal(0.4f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
                if (characterNPC2Location.distance(waypointb1) < 1) {
                    pointb1 = true;
                    pointb2 = false;
                }
            } else if (model.getWorldBound().intersects(modelNPC2.getWorldBound()) && safezone == false && killmob2 == false && loss == false) {
                walkNPC2 = sinBadLocation.subtract(characterNPC2Location).normalize().multLocal(0.0001f);
                npc2.setViewDirection(walkNPC2);
                if (!"Dodge".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Dodge", 0.7f);
                    animationChannelNPC2.setSpeed(0.5f);
                } else if ("Dodge".equals(animationChannelNPC2.getAnimationName())) {
                    oto2hitsin = true;
                }
            } else if (pointb1 == true && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = portalloc2.subtract(characterNPC2Location).normalize().multLocal(0.4f);
                if (!"Walk".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("Walk", 0.7f);
                    animationChannelNPC.setSpeed(1.5f);
                }
                npc2.setViewDirection(walkNPC2);
                cubeb = true;
                pointb1 = false;
            } else if (cubeb == true && portal2.getWorldBound().intersects(modelNPC2.getWorldBound()) && characterNPC2Location.distance(sinBadLocation) > aggrorange) {
                walkNPC2 = portalloc2.subtract(characterNPC2Location).normalize().multLocal(0.000001f);
                npc2.setViewDirection(walkNPC2);
                if (!"push".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("push", 0.7f);
                    animationChannelNPC2.setLoopMode(LoopMode.DontLoop);
                }
            } else if (loss == true) {
                if (!"stand".equals(animationChannelNPC2.getAnimationName())) {
                    animationChannelNPC2.setAnim("stand", 0.7f);
                }
            }
        }
    }

    private Spatial createSun() {
        Material stone_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        stone_mat.setColor("Color", ColorRGBA.Cyan);
        lightMdl = new Geometry("Light", new Sphere(240, 240, 1f));
        lightMdl.setMaterial(stone_mat);
        lightMdl.setLocalTranslation(new Vector3f(77.70334f, 34.013165f, 27.1017f));
        lightMdl.setLocalScale(10);
        rootNode.attachChild(lightMdl);
        return lightMdl;
    }

    private Spatial createMoon() {
        Material stone_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        stone_mat.setColor("Color", ColorRGBA.Magenta);
        nightMdl = new Geometry("Light", new Sphere(240, 240, 1f));
        nightMdl.setMaterial(stone_mat);
        nightMdl.setLocalTranslation(new Vector3f(-77.70334f, -34.013165f, -27.1017f));
        nightMdl.setLocalScale(5);
        rootNode.attachChild(nightMdl);
        return nightMdl;
    }

    private void createSky() {
        Texture north = assetManager.loadTexture("Textures/Sky/cliffsofinsanity/cliffsofinsanity_right.jpg");
        Texture south = assetManager.loadTexture("Textures/Sky/cliffsofinsanity/cliffsofinsanity_left.jpg");
        Texture east = assetManager.loadTexture("Textures/Sky/cliffsofinsanity/cliffsofinsanity_back.jpg");
        Texture west = assetManager.loadTexture("Textures/Sky/cliffsofinsanity/cliffsofinsanity_front.jpg");
        Texture up = assetManager.loadTexture("Textures/Sky/cliffsofinsanity/cliffsofinsanity_top.jpg");
        Texture down = assetManager.loadTexture("Textures/Sky/cliffsofinsanity/cliffsofinsanity_top.jpg");
        Spatial sky = SkyFactory.createSky(assetManager, west, east, north, south, up, down);
        rootNode.attachChild(sky);
    }

    protected Geometry makeCube(String name, float x, float y, float z) {
        Box box = new Box(new Vector3f(x, y, z), 1, 1, 1);
        Geometry cube = new Geometry(name, box);
        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.randomColor());
        cube.setMaterial(mat1);
        return cube;
    }

    protected void makePortalBlue() {
        Box b = new Box(5, 5, 5);
        portal1 = new Geometry("Portal", b);
        Material matprtlb = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        portal1_phy = new RigidBodyControl(0);
        portal1.addControl(portal1_phy);
        portal1.setLocalTranslation(new Vector3f(-197, 22, 97));
        portal1.setMaterial(matprtlb);
        RigidBodyControl testcon = new RigidBodyControl(CollisionShapeFactory.createMeshShape(portal1), 0);
        portal1.addControl(testcon);
        getPhysicsSpace().add(testcon);
        Texture portal2Tex = assetManager.loadTexture("Textures/portaltex.jpg");
        matprtlb.setTexture("ColorMap", portal2Tex);
        rootNode.attachChild(portal1);
    }

    protected void makePortalYellow() {
        Box b = new Box(5, 5, 5);
        portal2 = new Geometry("Portal", b);
        Material matprtly = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        portal2_phy = new RigidBodyControl(0);
        portal2.addControl(portal2_phy);
        portal2.setLocalTranslation(new Vector3f(-204, 14, -201));
        portal2.rotate(0, 45, 0);
        portal2.setMaterial(matprtly);
        RigidBodyControl testcon = new RigidBodyControl(CollisionShapeFactory.createMeshShape(portal2), 0);
        portal2.addControl(testcon);
        getPhysicsSpace().add(testcon);
        Texture portal2Tex = assetManager.loadTexture("Textures/portaltex.jpg");
        matprtly.setTexture("ColorMap", portal2Tex);
        rootNode.attachChild(portal2);
    }

    private void createTerrain2() {
        sceneModel = assetManager.loadModel("Scenes/map1.j3o");
        sceneModel.setLocalScale(1.0f);

        CollisionShape sceneShape = CollisionShapeFactory.createMeshShape((Node) sceneModel);
        landscape = new RigidBodyControl(sceneShape, 0);
        sceneModel.addControl(landscape);

        rootNode.attachChild(sceneModel);
        getPhysicsSpace().add(sceneModel);
    }

    private Spatial Trees(float x, float y, float z, float s) {
        tree = (Node) assetManager.loadModel("Models/Tree/Tree.j3o");
        tree.setLocalTranslation(x, y, z);
        tree.scale(s);
        return tree;
    }

    protected void initCrossHairs() {
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText ch = new BitmapText(guiFont, false);
        ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        ch.setText("*"); // crosshairs
        ch.setLocalTranslation( // center
                settings.getWidth() / 2 - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
        guiNode.attachChild(ch);
    }

    private void createCharacter() {
        CapsuleCollisionShape capsule = new CapsuleCollisionShape(1f, 3.3f, 1);
        character = new CharacterControl(capsule, 2.0f);
        model = (Node) assetManager.loadModel("Models/Sinbad/Sinbad.j3o");
        model.setLocalScale(0.5f);
        character.setJumpSpeed(20f);
        character.setGravity(30f);
        character.setFallSpeed(30f);
        character.setMaxSlope(1.1f);
        model.setUserData("SinHealth", sinHealth);
        model.addControl(character);
        character.setPhysicsLocation(new Vector3f(199, 6, 167));

        rootNode.attachChild(model);
        getPhysicsSpace().add(character);
    }

    private Spatial createHouse() {
        modelhouse = (Node) assetManager.loadModel("Models/Cottaged/Cottage.j3o");
        modelhouse.setLocalScale(0.07f);
        modelhouse.rotate(0, 90, 0);
        modelhouse.setLocalTranslation(new Vector3f(154, 23, 154));
        RigidBodyControl testcon = new RigidBodyControl(CollisionShapeFactory.createMeshShape(modelhouse), 0);
        modelhouse.addControl(testcon);
        modelhouse.setUserData("HouseHealth", houseHealth);
        getPhysicsSpace().add(testcon);
        rootNode.attachChild(modelhouse);
        return modelhouse;
    }

    public Spatial Logs(String name) {
        log = assetManager.loadModel("Models/low_obj_1500/low_obj_1500.j3o");
        log.scale(0.3f);
        return log;
    }

    public void createMolotov() {
        molotov = assetManager.loadModel("Models/REOB molotov/REOB Molotov.j3o");
        molotov.rotate(FastMath.HALF_PI, FastMath.HALF_PI, 0);
        molotov.setLocalScale(6);
        TangentBinormalGenerator.generate(molotov);
        molo.attachChild(molotov);

        molotov2 = assetManager.loadModel("Models/REOB molotov/REOB Molotov.j3o");
        molotov2.rotate(0, FastMath.HALF_PI, 0);
        molotov2.setLocalTranslation(148.5f, 18.5f, 146.5f);
        molotov2.setLocalScale(6);
        molo2.attachChild(molotov2);

        molotov3 = assetManager.loadModel("Models/REOB molotov/REOB Molotov.j3o");
        molotov3.rotate(0, FastMath.HALF_PI, 0);
        molotov3.setLocalTranslation(148, 18.5f, 148);
        molotov3.setLocalScale(6);
        molo3.attachChild(molotov3);

        molotov4 = assetManager.loadModel("Models/REOB molotov/REOB Molotov.j3o");
        molotov4.rotate(0, FastMath.HALF_PI, 0);
        molotov4.setLocalTranslation(147, 18.5f, 148);
        molotov4.setLocalScale(6);
        molo4.attachChild(molotov4);
    }

    public void createMolotov1() {
        Box b = new Box(0.2f, 0.4f, 0.2f); // create cube shape
        molotov1 = new Geometry("Vault", b);
        Material molmat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mol_phy = new RigidBodyControl(1f);
        molotov1.setMaterial(molmat);
        molmat.setColor("Color", ColorRGBA.Brown);
        moloshape = CollisionShapeFactory.createDynamicMeshShape(molotov1);
        moloth.attachChild(molotov1);
        molotov1.setLocalTranslation(molotov.getWorldTranslation().add(0, 4, 0));
        molotov1.addControl(mol_phy);
        bulletAppState.getPhysicsSpace().add(mol_phy);
        mol_phy.setLinearVelocity(cam.getDirection().mult(60));
    }

    public void createSword() {
        sword = assetManager.loadModel("Models/Killer_Frost_Ice_Sword/Killer_Frost_Ice_Sword.j3o");
        sword.rotate(0, 0, FastMath.HALF_PI);
        sword.setLocalTranslation(-112, -11, -79);
        sword.setLocalScale(6);
        TangentBinormalGenerator.generate(sword);
        blade.attachChild(sword);
    }

    public void createSwordEq() {
        sword2 = assetManager.loadModel("Models/Killer_Frost_Ice_Sword/Killer_Frost_Ice_Sword.j3o");
        sword2.rotate(0, 0, FastMath.HALF_PI);
        sword2.setLocalScale(6);
        TangentBinormalGenerator.generate(sword2);
        blade.attachChild(sword2);
    }

    public void createAxe() {
        axe = assetManager.loadModel("Models/ax/Ax1.j3o");
        axe.rotate(0, 0, 0);
        axe.setLocalTranslation(187, 17, -22);
        axe.setLocalScale(6);
        TangentBinormalGenerator.generate(axe);
        ax.attachChild(axe);
    }

    public void createAxeEq() {
        axe2 = assetManager.loadModel("Models/ax/Ax1.j3o");
        axe2.rotate(0, FastMath.HALF_PI, 0);
        axe2.setLocalScale(6);
        TangentBinormalGenerator.generate(axe2);
        ax.attachChild(axe2);
    }

    public Node getAttachmentsNodemolo() {
        SkeletonControl skeletonControl = model.getControl(SkeletonControl.class);
        skmol = skeletonControl.getAttachmentsNode("PinkyMed.L");
        return (skmol);
    }

    public Node getAttachmentsNodeaxe() {
        SkeletonControl skeletonControl = model.getControl(SkeletonControl.class);
        skaxe = skeletonControl.getAttachmentsNode("Handle.R");
        return (skaxe);
    }

    public Node getAttachmentsNodesword() {
        SkeletonControl skeletonControl = model.getControl(SkeletonControl.class);
        skswd = skeletonControl.getAttachmentsNode("Handle.R");
        return (skswd);
    }

    private void setupChaseCamera() {
        viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 1f, 1));
        flyCam.setEnabled(false);
        chaseCam = new ChaseCamera(cam, model, inputManager);
        chaseCam.setInvertVerticalAxis(true);
        chaseCam.setLookAtOffset(new Vector3f(0, 5f, 0));
    }

    private void createBoom() {
        flame = new ParticleEmitter("Flame", EMITTER_TYPE, 32 * COUNT_FACTOR);
        flame.setSelectRandomImage(true);
        flame.setStartColor(new ColorRGBA(1f, 0.4f, 0.05f, (float) (1f / COUNT_FACTOR_F)));
        flame.setEndColor(new ColorRGBA(.4f, .22f, .12f, 0f));
        flame.setStartSize(1.3f);
        flame.setEndSize(2f);
        flame.setShape(new EmitterSphereShape(Vector3f.ZERO, 1f));
        flame.setParticlesPerSec(0);
        flame.setGravity(0, -5, 0);
        flame.setLowLife(.4f);
        flame.setHighLife(.5f);
        flame.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 7, 0));
        flame.getParticleInfluencer().setVelocityVariation(1f);
        flame.setImagesX(2);
        flame.setImagesY(2);

        Material matflame = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        matflame.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/flame.png"));
        matflame.setBoolean("PointSprite", POINT_SPRITE);
        flame.setMaterial(matflame);
        explosionEffect.attachChild(flame);

        flash = new ParticleEmitter("Flash", EMITTER_TYPE, 24 * COUNT_FACTOR);
        flash.setSelectRandomImage(true);
        flash.setStartColor(new ColorRGBA(1f, 0.8f, 0.36f, (float) (1f / COUNT_FACTOR_F)));
        flash.setEndColor(new ColorRGBA(1f, 0.8f, 0.36f, 0f));
        flash.setStartSize(.1f);
        flash.setEndSize(3.0f);
        flash.setShape(new EmitterSphereShape(Vector3f.ZERO, .05f));
        flash.setParticlesPerSec(0);
        flash.setGravity(0, 0, 0);
        flash.setLowLife(.2f);
        flash.setHighLife(.2f);
        flash.setInitialVelocity(new Vector3f(0, 5f, 0));
        flash.setVelocityVariation(1);
        flash.setImagesX(2);
        flash.setImagesY(2);

        Material matflash = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        matflash.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/flash.png"));
        matflash.setBoolean("PointSprite", POINT_SPRITE);
        flash.setMaterial(matflash);
        explosionEffect.attachChild(flash);


        roundspark = new ParticleEmitter("RoundSpark", EMITTER_TYPE, 20 * COUNT_FACTOR);
        roundspark.setStartColor(new ColorRGBA(1f, 0.29f, 0.34f, (float) (1.0 / COUNT_FACTOR_F)));
        roundspark.setEndColor(new ColorRGBA(0, 0, 0, (float) (0.5f / COUNT_FACTOR_F)));
        roundspark.setStartSize(1.2f);
        roundspark.setEndSize(1.8f);
        roundspark.setShape(new EmitterSphereShape(Vector3f.ZERO, 2f));
        roundspark.setParticlesPerSec(0);
        roundspark.setGravity(0, -.5f, 0);
        roundspark.setLowLife(1.8f);
        roundspark.setHighLife(2f);
        roundspark.setInitialVelocity(new Vector3f(0, 3, 0));
        roundspark.setVelocityVariation(.5f);
        roundspark.setImagesX(1);
        roundspark.setImagesY(1);

        Material matroundspark = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        matroundspark.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/roundspark.png"));
        matroundspark.setBoolean("PointSprite", POINT_SPRITE);
        roundspark.setMaterial(matroundspark);
        explosionEffect.attachChild(roundspark);

        spark = new ParticleEmitter("Spark", ParticleMesh.Type.Triangle, 30 * COUNT_FACTOR);
        spark.setStartColor(new ColorRGBA(1f, 0.8f, 0.36f, (float) (1.0f / COUNT_FACTOR_F)));
        spark.setEndColor(new ColorRGBA(1f, 0.8f, 0.36f, 0f));
        spark.setStartSize(.5f);
        spark.setEndSize(.5f);
        spark.setFacingVelocity(true);
        spark.setParticlesPerSec(0);
        spark.setGravity(0, 5, 0);
        spark.setLowLife(1.1f);
        spark.setHighLife(1.5f);
        spark.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 20, 0));
        spark.getParticleInfluencer().setVelocityVariation(1);
        spark.setImagesX(1);
        spark.setImagesY(1);

        Material matspark = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        matspark.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/spark.png"));
        spark.setMaterial(matspark);
        explosionEffect.attachChild(spark);

        smoketrail = new ParticleEmitter("SmokeTrail", ParticleMesh.Type.Triangle, 22 * COUNT_FACTOR);
        smoketrail.setStartColor(new ColorRGBA(1f, 0.8f, 0.36f, (float) (1.0f / COUNT_FACTOR_F)));
        smoketrail.setEndColor(new ColorRGBA(1f, 0.8f, 0.36f, 0f));
        smoketrail.setStartSize(.2f);
        smoketrail.setEndSize(1f);
//        smoketrail.setShape(new EmitterSphereShape(Vector3f.ZERO, 1f));
        smoketrail.setFacingVelocity(true);
        smoketrail.setParticlesPerSec(0);
        smoketrail.setGravity(0, 1, 0);
        smoketrail.setLowLife(.4f);
        smoketrail.setHighLife(.5f);
        smoketrail.setInitialVelocity(new Vector3f(0, 12, 0));
        smoketrail.setVelocityVariation(1);
        smoketrail.setImagesX(1);
        smoketrail.setImagesY(3);

        Material matsmoketrail = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        matsmoketrail.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/smoketrail.png"));
        smoketrail.setMaterial(matsmoketrail);
        explosionEffect.attachChild(smoketrail);


        debris = new ParticleEmitter("Debris", ParticleMesh.Type.Triangle, 15 * COUNT_FACTOR);
        debris.setSelectRandomImage(true);
        debris.setRandomAngle(true);
        debris.setRotateSpeed(FastMath.TWO_PI * 4);
        debris.setStartColor(new ColorRGBA(1f, 0.59f, 0.28f, (float) (1.0f / COUNT_FACTOR_F)));
        debris.setEndColor(new ColorRGBA(.5f, 0.5f, 0.5f, 0f));
        debris.setStartSize(.2f);
        debris.setEndSize(.2f);
//        debris.setShape(new EmitterSphereShape(Vector3f.ZERO, .05f));
        debris.setParticlesPerSec(0);
        debris.setGravity(0, 12f, 0);
        debris.setLowLife(1.4f);
        debris.setHighLife(1.5f);
        debris.setInitialVelocity(new Vector3f(0, 15, 0));
        debris.setVelocityVariation(.60f);
        debris.setImagesX(3);
        debris.setImagesY(3);

        Material matdebris = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        matdebris.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/Debris.png"));
        debris.setMaterial(matdebris);
        explosionEffect.attachChild(debris);


        shockwave = new ParticleEmitter("Shockwave", ParticleMesh.Type.Triangle, 1 * COUNT_FACTOR);
//        shockwave.setRandomAngle(true);
        shockwave.setFaceNormal(Vector3f.UNIT_Y);
        shockwave.setStartColor(new ColorRGBA(.48f, 0.17f, 0.01f, (float) (.8f / COUNT_FACTOR_F)));
        shockwave.setEndColor(new ColorRGBA(.48f, 0.17f, 0.01f, 0f));
        shockwave.setStartSize(0f);
        shockwave.setEndSize(7f);
        shockwave.setParticlesPerSec(0);
        shockwave.setGravity(0, 0, 0);
        shockwave.setLowLife(0.5f);
        shockwave.setHighLife(0.5f);
        shockwave.setInitialVelocity(new Vector3f(0, 0, 0));
        shockwave.setVelocityVariation(0f);
        shockwave.setImagesX(1);
        shockwave.setImagesY(1);

        Material matshockwave = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        matshockwave.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/shockwave.png"));
        shockwave.setMaterial(matshockwave);
        explosionEffect.attachChild(shockwave);
    }

    private void createFire() {
        fire = new ParticleEmitter("Emitter", ParticleMesh.Type.Triangle, 30);
        Material mat_red = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        mat_red.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/flame.png"));
        fire.setMaterial(mat_red);
        fire.setImagesX(2);
        fire.setImagesY(2); // 2x2 texture animation
        fire.setEndColor(new ColorRGBA(1f, 0f, 0f, 1f));   // red
        fire.setStartColor(new ColorRGBA(1f, 1f, 0f, 0.5f)); // yellow
        fire.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 2, 0));
        fire.setStartSize(1.5f);
        fire.setEndSize(0.1f);
        fire.setGravity(0, 0, 0);
        fire.setLowLife(1f);
        fire.setHighLife(3f);

        fire.getParticleInfluencer().setVelocityVariation(0.3f);

    }

    protected void initMark() {
        Sphere sphere = new Sphere(30, 30, 0.2f);
        mark = new Geometry("BOOM!", sphere);
        Material mark_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mark_mat.setColor("Color", ColorRGBA.Red);
        mark.setMaterial(mark_mat);
    }

    private void createTree() {
        for (int i = 0; i <= 20; i++) {
            //Geometry geom = new Geometry("Tree");
            int x = rand.nextInt(210) + 15;
            int z = rand.nextInt(70) + 130;
            int s = rand.nextInt(6) + 5;

            cut.attachChild(Trees(-x, 15, z, s));

        }
        for (int i = 0; i <= 5; i++) {
            //Geometry geom = new Geometry("Tree");
            int x = rand.nextInt(61) + 15;
            int z = rand.nextInt(100) - 75;
            int s = rand.nextInt(6) + 5;

            cut.attachChild(Trees(x, 16, z, s));

        }
        for (int i = 0; i <= 5; i++) {
            //Geometry geom = new Geometry("Tree");
            int x = rand.nextInt(86) + 88;
            int z = rand.nextInt(37) + 18;
            int s = rand.nextInt(6) + 5;

            cut.attachChild(Trees(x, 16, -z, s));

        }
        for (int i = 0; i <= 50; i++) {
            //Geometry geom = new Geometry("Tree");
            int x = rand.nextInt(235) - 20;
            int z = rand.nextInt(80) + 150;
            int s = rand.nextInt(6) + 5;

            cut.attachChild(Trees(x, 5, -z, s));

        }
    }

    private void setupAnimationController() {
        animationControl = model.getControl(AnimControl.class);
        animationControl.addListener(this);
        animationChannel = animationControl.createChannel();
        animationChannel2 = animationControl.createChannel();
    }
    float angle;

    protected Geometry makeSwordPort(String name, float x, float y, float z) {
        Box box = new Box(new Vector3f(x, y, z), 1, 1, 1);
        Geometry cube = new Geometry(name, box);
        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat1.setColor("Color", ColorRGBA.randomColor());
        //Texture guiboxtex = assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg");
        Texture guiboxtex = assetManager.loadTexture("Textures/Mithril_sword_detail.png");
        mat1.setTexture("ColorMap", guiboxtex);
        cube.setMaterial(mat1);
        return cube;
    }

    protected Geometry makeAxePort(String name, float x, float y, float z) {
        Box box = new Box(new Vector3f(x, y, z), 1, 1, 1);
        Geometry cube = new Geometry(name, box);
        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat1.setColor("Color", ColorRGBA.randomColor());
        //Texture guiboxtex = assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg");
        Texture guiboxtex = assetManager.loadTexture("Textures/axetex.jpg");
        mat1.setTexture("ColorMap", guiboxtex);
        cube.setMaterial(mat1);
        return cube;
    }

    protected Geometry makeMoloPort(String name, float x, float y, float z) {
        Box box = new Box(new Vector3f(x, y, z), 1, 1, 1);
        Geometry cube = new Geometry(name, box);
        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat1.setColor("Color", ColorRGBA.randomColor());
        //Texture guiboxtex = assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg");
        Texture guiboxtex = assetManager.loadTexture("Textures/molotov.jpg");
        mat1.setTexture("ColorMap", guiboxtex);
        cube.setMaterial(mat1);
        return cube;
    }

    protected Geometry makeLogPort(String name, float x, float y, float z) {
        Box box = new Box(new Vector3f(x, y, z), 1, 1, 1);
        Geometry cube = new Geometry(name, box);
        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat1.setColor("Color", ColorRGBA.randomColor());
        //Texture guiboxtex = assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg");
        Texture guiboxtex = assetManager.loadTexture("Textures/wood.JPG");
        mat1.setTexture("ColorMap", guiboxtex);
        cube.setMaterial(mat1);
        return cube;
    }

    @Override
    public void simpleUpdate(float tpf) {
        super.simpleUpdate(tpf);
        character.setViewDirection(cam.getDirection());

        if (model.getWorldBound().intersects(modelhouse.getWorldBound())) {
            safezone = true;
        } else {
            safezone = false;
        }
        if (lightMdl.getLocalTranslation().y > 0) {
            day = true;

        } else if (lightMdl.getLocalTranslation().y <= 0) {
            day = false;

        }

        if (day == true) {
            aggrorange = 30;
            //System.out.println("Day");
        } else if (day == false) {
            aggrorange = 15;
            //System.out.println("Night");
        }

        if (oto1hithouse == true) {
            houseHealth = houseHealth - 1;
            modelhouse.setUserData("HouseHealth", houseHealth);
            oto1hithouse = false;
        }
        if (oto2hithouse == true) {
            houseHealth = houseHealth - 1;
            modelhouse.setUserData("HouseHealth", houseHealth);
            oto2hithouse = false;
        }
        if (oto1hitsin == true) {
            sinHealth = sinHealth - 1;
            model.setUserData("HouseHealth", sinHealth);
            oto1hitsin = false;
        }
        if (oto2hitsin == true) {
            sinHealth = sinHealth - 1;
            model.setUserData("HouseHealth", sinHealth);
            oto2hitsin = false;
        }


        if (playactivated == true) {
            starthud.detachAllChildren();
            if (sinHealth > 0) {
                sinhpport.detachAllChildren();
                sinhp.setText("" + sinHealth);
                sinhp.setLocalTranslation(90, 704, 1);
                sinhpport.attachChild(sinhp);
            } else {
                losscounter++;
                sinhpport.detachAllChildren();
                inputManager.clearMappings();
                loss = true;
            }
            if (houseHealth > 0) {
                househpport.detachAllChildren();
                househp.setText("" + houseHealth);
                househp.setLocalTranslation(78, 550, 1);
                househpport.attachChild(househp);
            } else {
                househpport.detachAllChildren();
            }
            if (npc1Health > 0) {
                npc1hpport.detachAllChildren();
                npc1hp.setText("" + npc1Health);
                npc1hp.setLocalTranslation(1070, 700, 1);
                npc1hpport.attachChild(npc1hp);

            } else {
                kill1 = true;
                kill1counter++;
                explosion_light.setPosition(new Vector3f(npc1.getPhysicsLocation()));
                explosionEffect.setLocalTranslation(npc1.getPhysicsLocation());
                time += tpf / speed;
                explosion = true;



                if (npc1.getPhysicsLocation().distance(npc2.getPhysicsLocation()) <= 15) {
                    molohitnpc2 = true;
                }

                if (time > 0.1f && state == 0) {
                    flash.emitAllParticles();
                    spark.emitAllParticles();
                    smoketrail.emitAllParticles();
                    debris.emitAllParticles();
                    shockwave.emitAllParticles();
                    explosion_light.setRadius(40f);
                    state++;
                }

                if (time > 0.1f + .05f / speed && state == 1) {
                    flame.emitAllParticles();
                    roundspark.emitAllParticles();
                    explosion_light.setRadius(0.0000000000001f);
                    state++;
                }
                // rewind the effect
                if (time > 4.5 / speed && state == 2) {
                    flash.killAllParticles();
                    spark.killAllParticles();
                    smoketrail.killAllParticles();
                    debris.killAllParticles();
                    flame.killAllParticles();
                    roundspark.killAllParticles();
                    shockwave.killAllParticles();
                    explosion_light.setRadius(0.0000000000001f);
                }
                npc1hpport.detachAllChildren();
                mob1.detachAllChildren();
            }
            if (npc2Health > 0) {
                npc2hpport.detachAllChildren();
                npc2hp.setText("" + npc2Health);
                npc2hp.setLocalTranslation(1210, 700, 1);
                npc2hpport.attachChild(npc2hp);
            } else {
                kill2 = true;
                kill2counter++;
                explosion_light.setPosition(new Vector3f(npc2.getPhysicsLocation()));
                explosionEffect.setLocalTranslation(npc2.getPhysicsLocation());
                time += tpf / speed;
                explosion = true;



                if (npc2.getPhysicsLocation().distance(npc1.getPhysicsLocation()) <= 15) {
                    molohitnpc1 = true;
                }

                if (time > 0.1f && state == 0) {
                    flash.emitAllParticles();
                    spark.emitAllParticles();
                    smoketrail.emitAllParticles();
                    debris.emitAllParticles();
                    shockwave.emitAllParticles();
                    explosion_light.setRadius(40f);
                    state++;
                }

                if (time > 0.1f + .05f / speed && state == 1) {
                    flame.emitAllParticles();
                    roundspark.emitAllParticles();
                    explosion_light.setRadius(0.0000000000001f);
                    state++;
                }
                // rewind the effect
                if (time > 4.5 / speed && state == 2) {
                    flash.killAllParticles();
                    spark.killAllParticles();
                    smoketrail.killAllParticles();
                    debris.killAllParticles();
                    flame.killAllParticles();
                    roundspark.killAllParticles();
                    shockwave.killAllParticles();
                    explosion_light.setRadius(0.0000000000001f);
                }
                npc2hpport.detachAllChildren();
                mob2.detachAllChildren();
            }
            if (npc1Health <= 0 && npc2Health <= 0) {
                inputManager.clearMappings();
                npc1hpport.detachAllChildren();
                npc2hpport.detachAllChildren();

                wincounter++;
                win = true;


            }
            if (score >= 0) {
                scoretxt.setText("" + score);
                scoretxt.setLocalTranslation(1170, 50, 1);
                scoreport.attachChild(scoretxt);
            }
        } else if (playactivated == false) {
            starthud.attachChild(startpic);
        }

        if (win == true && wincounter == 1) {

            guiNode.attachChild(victorypic);
            scoreport.detachAllChildren();
            score = score + houseHealth;
            score = score + sinHealth;
            scoretxt.setText("" + score);
            scoreport.attachChild(scoretxt);
            win = false;

        }
        if (loss == true && losscounter == 1) {
            guiNode.attachChild(losspic);
            scoretxt.setText("" + score);
            scoreport.attachChild(scoretxt);
        }
        if (loss == true) {
            if (!"StandUpBack".equals(animationChannel.getAnimationName())) {
                animationChannel.setAnim("StandUpBack", 0.7f);
                animationChannel.setSpeed(-0.7f);
                animationChannel.setLoopMode(LoopMode.DontLoop);
            }
        }

        if (kill1 == true && kill1counter == 1) {
            scoreport.detachAllChildren();
            score = score + 100;
            scoretxt.setText("" + score);
            scoreport.attachChild(scoretxt);
            killmob1 = true;
            kill1 = false;
        }
        if (kill2 == true && kill2counter == 1) {
            scoreport.detachAllChildren();
            score = score + 100;
            scoretxt.setText("" + score);
            scoreport.attachChild(scoretxt);
            killmob2 = true;
            kill2 = false;
        }

        if (swordpressed == true) {
            swordmsgtimer++;
            if (swordmsgtimer < 80) {
                swordmsg.setText("You have to loot the Frozen Tear before you try to equip it!");
                swordmsg.setLocalTranslation(settings.getWidth() / 2 - 195,
                        settings.getHeight() / 2 + 40, 0);
                notifications.attachChild(swordmsg);
            } else if (swordmsgtimer < 160) {
                notifications.detachAllChildren();
                swordmsg.setText("The legend says that it rest at the bottom of the lake!");
                swordmsg.setLocalTranslation(settings.getWidth() / 2 - 180,
                        settings.getHeight() / 2 + 40, 0);
                notifications.attachChild(swordmsg);
            } else if (swordmsgtimer < 240) {
                notifications.detachAllChildren();
                swordmsgtimer = 0;
                swordpressed = false;
            }
        }
        if (axepressed == true) {
            axemsgtimer++;
            if (axemsgtimer < 80) {
                axemsg.setText("You have to loot the axe before you try to equip it!");
                axemsg.setLocalTranslation(settings.getWidth() / 2 - 175,
                        settings.getHeight() / 2 + 40, 0);
                notifications.attachChild(axemsg);
            } else if (axemsgtimer < 160) {
                notifications.detachAllChildren();
                axemsg.setText("The last time you saw it, it was under a tree!");
                axemsg.setLocalTranslation(settings.getWidth() / 2 - 160,
                        settings.getHeight() / 2 + 40, 0);
                notifications.attachChild(axemsg);
            } else if (axemsgtimer < 240) {
                notifications.detachAllChildren();
                axemsgtimer = 0;
                axepressed = false;
            }
        }
        if (molotovpressed == true) {
            molotovmsgtimer++;
            if (molotovmsgtimer < 80) {
                molotovmsg.setText("You have to loot the flamable beverage before you try to equip it!");
                molotovmsg.setLocalTranslation(settings.getWidth() / 2 - 210,
                        settings.getHeight() / 2 + 40, 0);
                notifications.attachChild(molotovmsg);
            } else if (molotovmsgtimer < 160) {
                notifications.detachAllChildren();
                molotovmsg.setText("You usually keep some on your table inside your Cottage!");
                molotovmsg.setLocalTranslation(settings.getWidth() / 2 - 180,
                        settings.getHeight() / 2 + 40, 0);
                notifications.attachChild(molotovmsg);
            } else if (molotovmsgtimer < 240) {
                notifications.detachAllChildren();
                molotovmsg.setText("If you used them all bring back some wood!");
                molotovmsg.setLocalTranslation(settings.getWidth() / 2 - 160,
                        settings.getHeight() / 2 + 40, 0);
                notifications.attachChild(molotovmsg);
            } else if (molotovmsgtimer < 320) {
                notifications.detachAllChildren();
                molotovmsg.setText("You extract its resin to produce the beverage!");
                molotovmsg.setLocalTranslation(settings.getWidth() / 2 - 165,
                        settings.getHeight() / 2 + 40, 0);
                notifications.attachChild(molotovmsg);
            } else if (molotovmsgtimer < 400) {
                notifications.detachAllChildren();
                molotovmsgtimer = 0;
                molotovpressed = false;
            }
        }
        if (reppressed == true) {
            repmsgtimer++;
            if (repmsgtimer < 80) {
                repmsg.setText("You need to have at least 2 logs and be inside your Home to repair it!");
                repmsg.setLocalTranslation(settings.getWidth() / 2 - 180,
                        settings.getHeight() / 2 + 40, 0);
                notifications.attachChild(repmsg);
            } else {
                textNode.detachAllChildren();
                molotovmsgtimer = 0;
                reppressed = false;
            }
        }
        if (onair == true) {
            fire.setLocalTranslation(mol_phy.getPhysicsLocation());
            molotovhit = false;
            molotov_light.setRadius(7f);
            explosion_light.setRadius(0.000000000001f);
            molotov_light.setPosition(new Vector3f(mol_phy.getPhysicsLocation()));
            time2++;
        }

        if (onair == true && molotov1.getWorldBound().intersects(shootables.getWorldBound())) {
            molotovhit = true;
        }

        if (molotovhit == true) {

            explosion_light.setPosition(new Vector3f(mol_phy.getPhysicsLocation()));
            explosionEffect.setLocalTranslation(mol_phy.getPhysicsLocation());
            time += tpf / speed;
            explosion = true;
            molotov_light.setRadius(0.00000000001f);

            if (mol_phy.getPhysicsLocation().distance(npc1.getPhysicsLocation()) <= 15) {
                molohitnpc1 = true;
            }
            if (mol_phy.getPhysicsLocation().distance(npc2.getPhysicsLocation()) <= 15) {
                molohitnpc2 = true;
            }

            if (time > 0.1f && state == 0) {
                flash.emitAllParticles();
                spark.emitAllParticles();
                smoketrail.emitAllParticles();
                debris.emitAllParticles();
                shockwave.emitAllParticles();
                explosion_light.setRadius(40f);
                state++;

            }
            if (time > 0.1f + .05f / speed && state == 1) {
                flame.emitAllParticles();
                roundspark.emitAllParticles();
                explosion_light.setRadius(0.0000000000001f);
                state++;
            }

            // rewind the effect
            if (time > 4.5 / speed && state == 2) {
                flash.killAllParticles();
                spark.killAllParticles();
                smoketrail.killAllParticles();
                debris.killAllParticles();
                flame.killAllParticles();
                roundspark.killAllParticles();
                shockwave.killAllParticles();
                explosion_light.setRadius(0.0000000000001f);
            }
            moloth.detachAllChildren();
            onair = false;
            molotovhit = false;
        } else if (time2 == 50) {
            explosion_light.setPosition(new Vector3f(mol_phy.getPhysicsLocation()));
            explosionEffect.setLocalTranslation(mol_phy.getPhysicsLocation());
            time += tpf / speed;
            explosion = true;

            if (mol_phy.getPhysicsLocation().distance(npc1.getPhysicsLocation()) <= 15) {
                molohitnpc1 = true;
            }
            if (mol_phy.getPhysicsLocation().distance(npc2.getPhysicsLocation()) <= 15) {
                molohitnpc2 = true;
            }

            molotov_light.setRadius(0.00000000001f);

            if (time > 0.1f && state == 0) {
                flash.emitAllParticles();
                spark.emitAllParticles();
                smoketrail.emitAllParticles();
                debris.emitAllParticles();
                shockwave.emitAllParticles();
                explosion_light.setRadius(4000f);
                state++;
            }

            if (time > 0.1f + .05f / speed && state == 1) {
                flame.emitAllParticles();
                roundspark.emitAllParticles();
                explosion_light.setRadius(0.0000000000001f);
                state++;
            }
            // rewind the effect
            if (time > 4.5 / speed && state == 2) {
                flash.killAllParticles();
                spark.killAllParticles();
                smoketrail.killAllParticles();
                debris.killAllParticles();
                flame.killAllParticles();
                roundspark.killAllParticles();
                shockwave.killAllParticles();
                explosion_light.setRadius(0.0000000000001f);
            }
            moloth.detachAllChildren();
            onair = false;
            molotovhit = false;
        }

        if (molohitnpc1 == true) {
            npc1hpport.detachAllChildren();
            npc1Health = npc1Health - 40;
            modelNPC.setUserData("npc1Health", npc1Health);
            npc1hp.setText("" + npc1Health);
            //System.out.println("npc1 " + modelNPC.getUserData("npc1Health"));
            npc1hpport.attachChild(npc1hp);

            scoreport.detachAllChildren();
            score = score + 5;
            scoretxt.setText("" + score);
            scoreport.attachChild(scoretxt);

            molohitnpc1 = false;
        }

        if (molohitnpc2 == true) {
            npc2hpport.detachAllChildren();
            npc2Health = npc2Health - 40;
            modelNPC2.setUserData("npc2Health", npc2Health);
            npc2hp.setText("" + npc2Health);
            //System.out.println("npc2 " + modelNPC2.getUserData("npc2Health"));
            npc2hpport.attachChild(npc2hp);

            scoreport.detachAllChildren();
            score = score + 5;
            scoretxt.setText("" + score);
            scoreport.attachChild(scoretxt);

            molohitnpc2 = false;
        }


        if (playactivated == true) {
            setupMotionPatha();
            setupMotionPathb();
        }

        npc1.setWalkDirection(walkNPC);
        npc2.setWalkDirection(walkNPC2);

        angle += 0.03 * tpf;
        angle %= FastMath.TWO_PI;
        spot.setPosition(new Vector3f(30f, FastMath.cos(angle) * 512.013165f, FastMath.sin(angle) * 512f));
        lightMdl.setLocalTranslation(spot.getPosition());
        spot.setDirection(lightTarget.subtract(spot.getPosition()));

        spot2.setPosition(new Vector3f(-30f, -FastMath.cos(angle) * 512.013165f, -FastMath.sin(angle) * 512f));
        nightMdl.setLocalTranslation(spot2.getPosition());
        spot2.setDirection(lightTarget.subtract(spot2.getPosition()));

        Vector3f camDir = cam.getDirection().clone().multLocal(0.5f); //speed
        Vector3f camLeft = cam.getLeft().clone().multLocal(0.2f);
        camDir.y = 0;
        camLeft.y = 0;
        walkDirection.set(0, 0, 0);
        if (left) {
            walkDirection.addLocal(camLeft);

        }
        if (right) {
            walkDirection.addLocal(camLeft.negate());

        }
        if (up) {
            walkDirection.addLocal(camDir);

        }
        if (down) {
            walkDirection.addLocal(cam.getDirection().clone().multLocal(-0.2f));

        }
        if (!character.onGround()) {
            airTime = airTime + tpf;

        } else {
            airTime = 0;

        }
        if (walkDirection.length() == 0) {
            if (!"IdleTop".equals(animationChannel.getAnimationName())) {
                animationChannel.setAnim("IdleTop", 1f);
                animationChannel2.setAnim("IdleBase", 0.7f);
            }
        } else {
            character.setViewDirection(walkDirection);
            if (airTime > .3f) {
                if (!"IdleTop".equals(animationChannel.getAnimationName())) {
                    animationChannel.setAnim("IdleTop");
                }
            } else if (!"RunBase".equals(animationChannel.getAnimationName())) {
                animationChannel.setAnim("RunBase", 0.7f);
                animationChannel2.setAnim("RunTop", 0.7f);
            }
        }
        character.setWalkDirection(walkDirection);
    }

    public void onAction(String binding, boolean value, float tpf) {
        if (binding.equals("CharLeft")) {
            if (value) {
                left = true;
            } else {
                left = false;
            }
        } else if (binding.equals("CharRight")) {
            if (value) {
                right = true;
            } else {
                right = false;
            }
        } else if (binding.equals("CharUp")) {
            if (value) {
                up = true;
            } else {
                up = false;
            }
        } else if (binding.equals("CharDown")) {
            if (value) {
                down = true;
            } else {
                down = false;
            }
        } else if (binding.equals("CharSpace")) {
            character.jump();
            System.out.println(model.getLocalTranslation());
        }/*else if (binding.equals("Play")) {
         motionControl.play();
         }*/ else if (binding.equals("Repair") && model.getWorldBound().intersects(modelhouse.getWorldBound()) && nlogs >= 2) {
            nlogs = nlogs - 1;
            BitmapText helloText = new BitmapText(guiFont, false);
            helloText.setSize(guiFont.getCharSet().getRenderedSize());
            logsinv.detachAllChildren();
            helloText.setText("" + nlogs);
            helloText.setLocalTranslation(145, 48, 1);
            logsinv.attachChild(helloText);
            if (houseHealth < 1000000) {
                houseHealth = houseHealth + 100;
            }
            if (usedmolo2 == true) {
                molo2.attachChild(molotov2);
                usedmolo2 = false;
            }
            if (usedmolo3 == true) {
                molo3.attachChild(molotov3);
                usedmolo3 = false;
            }
            if (usedmolo4 == true) {
                molo4.attachChild(molotov4);
                usedmolo4 = false;
            }
            scoreport.detachAllChildren();
            score = score + 2;
            scoretxt.setText("" + score);
            scoreport.attachChild(scoretxt);

        } else if (binding.equals("Repair") && !model.getWorldBound().intersects(modelhouse.getWorldBound()) || binding.equals("Repair") && nlogs < 2) {
            notifications.detachAllChildren();
            reppressed = true;
            repmsg = new BitmapText(guiFont, false);
            repmsg.setSize(guiFont.getCharSet().getRenderedSize());
        } else if (binding.equals("EquipSword") && swordloot == true) {
            if (swordeq == false && righteq == false) {
                Geometry guiBox = makeSwordPort("For Inventory", 0, 0, 0);
                guiBox.setLocalScale(20);
                guiBox.setLocalTranslation(147, 631, 1);
                weaprightport.attachChild(guiBox);
                animationChannel.setAnim("SliceVertical", 1f);
                skswd.attachChild(sword2);
                righteq = true;
                swordeq = true;
            }
        } else if (binding.equals("EquipSword") && swordloot == false) {
            notifications.detachAllChildren();
            swordpressed = true;
            swordmsg = new BitmapText(guiFont, false);
            swordmsg.setSize(guiFont.getCharSet().getRenderedSize());
        } else if (binding.equals("EquipAxe") && axeloot == true) {
            if (axeeq == false && righteq == false) {
                Geometry guiBox = makeAxePort("For Inventory", 0, 0, 0);
                guiBox.setLocalScale(20);
                guiBox.setLocalTranslation(147, 631, 1);
                weaprightport.attachChild(guiBox);
                animationChannel.setAnim("SliceVertical", 1f);
                skaxe.attachChild(axe2);
                righteq = true;
                axeeq = true;
            }
        } else if (binding.equals("EquipAxe") && axeloot == false) {
            notifications.detachAllChildren();
            axepressed = true;
            axemsg = new BitmapText(guiFont, false);
            axemsg.setSize(guiFont.getCharSet().getRenderedSize());
        } else if (binding.equals("EquipMolotov") && molotovloot == true) {
            if (molotoveq == false && lefteq == false && nmolo > 0) {
                Geometry guiBox = makeMoloPort("For Inventory", 0, 0, 0);
                guiBox.setLocalScale(20);
                guiBox.setLocalTranslation(67, 631, 1);
                weapleftport.attachChild(guiBox);
                animationChannel.setAnim("SliceVertical", 1f);
                skmol.attachChild(molotov);
                lefteq = true;
                molotoveq = true;
            }
        } else if (binding.equals("EquipMolotov") && molotovloot == false) {
            notifications.detachAllChildren();
            molotovpressed = true;
            molotovmsg = new BitmapText(guiFont, false);
            molotovmsg.setSize(guiFont.getCharSet().getRenderedSize());
        } else if (binding.equals("UnequipRight")) {
            weaprightport.detachAllChildren();
            if (righteq == true && swordeq == true) {
                animationChannel.setAnim("SliceVertical", 1f);
                skswd.detachAllChildren();
                righteq = false;
                swordeq = false;
            } else if (righteq == true && axeeq == true) {
                animationChannel.setAnim("SliceVertical", 1f);
                skaxe.detachAllChildren();
                righteq = false;
                axeeq = false;
            }
        } else if (binding.equals("UnequipLeft")) {
            weapleftport.detachAllChildren();
            if (lefteq == true) {
                animationChannel.setAnim("SliceVertical", 1f);
                skmol.detachAllChildren();
                lefteq = false;
                molotoveq = false;
            }
        } else if (binding.equals("Throw") && !value && molotoveq == true && nmolo > 0) {
            animationChannel.setAnim("SliceVertical", 0.5f);
            createMolotov1();
            onair = true;
            moloth.attachChild(fire);
            skmol.detachAllChildren();
            lefteq = false;
            molotoveq = false;
            explosion = false;
            state = 0;
            time = 0;
            time2 = 0;
            nmolo--;
            weapleftport.detachAllChildren();
            BitmapText helloText = new BitmapText(guiFont, false);
            helloText.setSize(guiFont.getCharSet().getRenderedSize());
            moloinv.detachAllChildren();
            helloText.setText("X" + nmolo);
            helloText.setLocalTranslation(65, 48, 1);
            moloinv.attachChild(helloText);
            if (nmolo == 0) {
                molotovloot = false;
            }



        } else if (binding.equals("Attack") && swordeq == true) {

            animationChannel.setAnim("SliceHorizontal", 0.5f);
            if (sword2.getWorldBound().intersects(modelNPC.getWorldBound())) {
                npc1hpport.detachAllChildren();
                npc1Health = npc1Health - 5;
                modelNPC.setUserData("npc1Health", npc1Health);
                npc1hp.setText("" + npc1Health);
                npc1hpport.attachChild(npc1hp);

                scoreport.detachAllChildren();
                score = score + 2;
                scoretxt.setText("" + score);
                scoreport.attachChild(scoretxt);
            }
            if (sword2.getWorldBound().intersects(modelNPC2.getWorldBound())) {
                npc2hpport.detachAllChildren();
                npc2Health = npc2Health - 5;
                modelNPC2.setUserData("npc2Health", npc2Health);
                npc2hp.setText("" + npc2Health);
                npc2hpport.attachChild(npc2hp);

                scoreport.detachAllChildren();
                score = score + 2;
                scoretxt.setText("" + score);
                scoreport.attachChild(scoretxt);
            }
        } else if (binding.equals("Play")) {
            sinhp = new BitmapText(guiFont, false);
            sinhp.setSize(guiFont.getCharSet().getRenderedSize());
            npc1hp = new BitmapText(guiFont, false);
            npc1hp.setSize(guiFont.getCharSet().getRenderedSize());
            npc2hp = new BitmapText(guiFont, false);
            npc2hp.setSize(guiFont.getCharSet().getRenderedSize());
            househp = new BitmapText(guiFont, false);
            househp.setSize(guiFont.getCharSet().getRenderedSize());
            scoretxt = new BitmapText(guiFont, false);
            scoretxt.setSize(guiFont.getCharSet().getRenderedSize());
            canttouch.play();
            playactivated = true;
            //setupMotionPath();
        } else if (binding.equals("Melee") && !value) {
            // 1. Reset results list.

            // 1. Reset results list.
            CollisionResults results = new CollisionResults();
            // 2. Aim the ray from cam loc to cam direction.
            Ray ray = new Ray(cam.getLocation(), cam.getDirection());
            // 3. Collect intersections between Ray and Shootables in results list.
            cut.collideWith(ray, results);
            pick.collideWith(ray, results);
            shootables.collideWith(ray, results);
            molo.collideWith(ray, results);
            molo2.collideWith(ray, results);
            molo3.collideWith(ray, results);
            molo4.collideWith(ray, results);
            ax.collideWith(ray, results);
            blade.collideWith(ray, results);
            // 4. Print the results
            System.out.println("----- Collisions? " + results.size() + "-----");
            for (int i = 0; i < results.size(); i++) {
                // For each hit, we know distance, impact point, name of geometry.
                float dist = results.getCollision(i).getDistance();
                Vector3f pt = results.getCollision(i).getContactPoint();
                String hit = results.getCollision(i).getGeometry().getName();
                System.out.println("* Collision #" + i);
                System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
            }
            // 5. Use the results (we mark the hit object)
            if (results.size() > 0) {
                // The closest collision point is what was truly hit:
                CollisionResult closest = results.getClosestCollision();

                // Get the geometry of the closest
                Geometry g = closest.getGeometry();

                System.out.println("The object=" + g);

                /*Geometry guiBox = makeCube("For Inventory", 0, 0, 0);
                 Material guiboxmat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                 guiboxmat.setColor("Color", ColorRGBA.Brown);
                 guiBox.setMaterial(guiboxmat);*/
                /*Texture guiboxtex = assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg");
                 //Texture guiboxtex = assetManager.loadTexture("Textures/BarkDecidious0194_7_S.jpg");
                 guiboxmat.setTexture("Texture", guiboxtex);*/


                Node p = g.getParent();
                Node q = p.getParent();
                System.out.println("Parent of shot object=" + p);
                System.out.println("Grandparent of the shot object=" + q);


                if (character.getPhysicsLocation().distance(g.getWorldTranslation()) <= interactrange) {


                    if ((p == pick) || (q == pick)) {
                        nlogs++;
                        if (p == pick) {
                            pick.detachChild(g);
                            scoreport.detachAllChildren();
                            score = score + 1;
                            scoretxt.setText("" + score);
                            scoreport.attachChild(scoretxt);
                            if (nlogs >= 0) {
                                Geometry guiBox = makeLogPort("For Inventory", 0, 0, 0);
                                guiBox.setLocalScale(20);
                                guiBox.setLocalTranslation(120, 38, 0);
                                guiNode.attachChild(guiBox);
                                guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
                                BitmapText helloText = new BitmapText(guiFont, false);
                                helloText.setSize(guiFont.getCharSet().getRenderedSize());
                                logsinv.detachAllChildren();
                                helloText.setText("" + nlogs);
                                helloText.setLocalTranslation(145, 48, 1);
                                logsinv.attachChild(helloText);
                            } else if (q == pick) {
                                pick.detachChild(p);
                            }
                        }
                    }
                    if ((p == cut) && axeeq == true || (q == cut) && axeeq == true) {
                        scoreport.detachAllChildren();
                        score = score + 1;
                        scoretxt.setText("" + score);
                        scoreport.attachChild(scoretxt);
                        if (p == cut) {
                            pick.attachChild(Logs("Log"));
                            log.setLocalTranslation(g.getWorldTranslation().add(0, 1.3f, 0));
                            cut.detachChild(g);

                        } else if (q == cut) {
                            pick.attachChild(Logs("Log"));
                            log.setLocalTranslation(p.getWorldTranslation().add(0, 1.3f, 0));
                            cut.detachChild(p);
                        }
                    }
                    if ((p == shootables) || (q == shootables)) {
                        if (p == shootables) {
                            shootables.detachChild(g);
                        } else if (q == shootables) {
                            shootables.detachChild(p);
                        }
                    }
                    if ((p == ax) || (q == ax)) {
                        if (p == ax) {
                            axeloot = true;
                            ax.detachChild(g);
                        } else if (q == ax) {
                            ax.detachChild(p);
                        }
                    }
                    if ((p == blade) || (q == blade)) {
                        if (p == blade) {
                            swordloot = true;
                            blade.detachChild(g);
                        } else if (q == blade) {
                            blade.detachChild(p);
                        }
                    }
                    if ((p == molo2) || (q == molo2)) {
                        nmolo++;
                        molotovloot = true;
                        if (p == molo2) {
                            molo2.detachChild(g);
                            usedmolo2 = true;
                            if (nmolo >= 0) {
                                Geometry guiBox = makeMoloPort("For Inventory", 0, 0, 0);
                                guiBox.setLocalScale(20);
                                //guiBox.setLocalTranslation(70, 625, 1);
                                weapleftport.attachChild(guiBox);
                                guiBox.setLocalTranslation(40, 38, 0);
                                guiNode.attachChild(guiBox);
                                guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
                                BitmapText helloText = new BitmapText(guiFont, false);
                                helloText.setSize(guiFont.getCharSet().getRenderedSize());
                                moloinv.detachAllChildren();
                                helloText.setText("X" + nmolo);
                                helloText.setLocalTranslation(65, 48, 1);
                                moloinv.attachChild(helloText);
                            }
                        } else if (q == molo2) {
                            molo2.detachChild(p);
                        }
                    }
                    if ((p == molo3) || (q == molo3)) {
                        nmolo++;
                        if (p == molo3) {
                            molo3.detachChild(g);
                            usedmolo3 = true;
                            if (nmolo >= 0) {
                                Geometry guiBox = makeMoloPort("For Inventory", 0, 0, 0);
                                guiBox.setLocalScale(20);
                                //guiBox.setLocalTranslation(70, 625, 1);
                                weapleftport.attachChild(guiBox);
                                guiBox.setLocalTranslation(40, 38, 0);
                                guiNode.attachChild(guiBox);
                                guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
                                BitmapText helloText = new BitmapText(guiFont, false);
                                helloText.setSize(guiFont.getCharSet().getRenderedSize());
                                moloinv.detachAllChildren();
                                helloText.setText("X" + nmolo);
                                helloText.setLocalTranslation(65, 48, 1);
                                moloinv.attachChild(helloText);
                            }
                        } else if (q == molo3) {
                            molo3.detachChild(p);
                        }
                    }
                    if ((p == molo4) || (q == molo4)) {
                        nmolo++;
                        if (p == molo4) {
                            molo4.detachChild(g);
                            usedmolo4 = true;
                            if (nmolo >= 0) {
                                Geometry guiBox = makeMoloPort("For Inventory", 0, 0, 0);
                                guiBox.setLocalScale(20);
                                //guiBox.setLocalTranslation(70, 625, 1);
                                weapleftport.attachChild(guiBox);
                                guiBox.setLocalTranslation(40, 38, 0);
                                guiNode.attachChild(guiBox);
                                guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
                                BitmapText helloText = new BitmapText(guiFont, false);
                                helloText.setSize(guiFont.getCharSet().getRenderedSize());
                                moloinv.detachAllChildren();
                                helloText.setText("X" + nmolo);
                                helloText.setLocalTranslation(65, 48, 1);
                                moloinv.attachChild(helloText);
                            }
                        } else if (q == molo4) {
                            molo4.detachChild(p);
                        }
                    }
                    // Let's interact - we mark the hit with a red dot.
                    mark.setLocalTranslation(closest.getContactPoint());
                    //rootNode.attachChild(mark);
                } else {

                    // No hits? Then remove the red mark.
                    rootNode.detachChild(mark);
                }
            }
        }
    }

    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        if (channel == shootingChannel) {
            channel.setAnim("stand");
        }
    }

    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
    }
}
