/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.gameobjects.playerObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.GeoWars;
import com.group17.geowars.gameobjects.*;
import com.group17.geowars.gameobjects.PowerUps.POWERUPTYPE;
import com.group17.geowars.gameobjects.PowerUps.PowerDown_Stats;
import com.group17.geowars.gameobjects.PowerUps.PowerUp;
import com.group17.geowars.gameobjects.PowerUps.Power_UpPassive;
import com.group17.geowars.gameobjects.hostileObjects.Enemy;
import com.group17.geowars.managers.AssetManager;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Player;
import com.group17.geowars.screens.MenuScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author kevin
 */
public abstract class Ship extends GameObject implements GOInterface { //interface shoot?    extends DynamicGameObject ?
    protected int maxHp;
    protected int hp;
    protected int attack;
    public boolean dead;
    protected int exp;
    protected int level;
    protected int score;
    protected int scorehealer;
    protected int multiplier;
    protected BitmapFont font;
    protected String type;
    protected Sprite shipSprite;
    protected Color shipColor;
    protected Sprite shield;
    protected Color shieldColor;
    protected Sprite greenHp;
    protected Color green;
    protected Sprite redHp;
    protected Color red;
    protected float fireDelay;
    protected int speed;
    protected Player player;
    protected ArrayList<PowerUp> powerups;
    protected BitmapFont bigFont;

    protected Vector2 popUpTextPos;


    //protected Sound sound;

    protected boolean canShoot = true;
    protected float timer = 0;
    protected float hitTime = 0;

    protected Vector2 shootDir = new Vector2(0, 0);
    protected Vector2 moveDir = new Vector2(0, 0);
    protected Vector2 lookDir = new Vector2(0, 0);

    public void setPlayer(Player p) {
        player = p;
    }

    public int getScore() {
        return score;
    }

    public Ship(Vector2 pos, String type) {
        super(pos);
        fireDelay = 0.15f;
        speed = 450;
        maxHp = 1;
        shipColor = Managers.getAssetManager().getShipColor();
        //sound = Managers.getAssetManager().getSounds("sounds/shot.mp");

        font = Managers.getAssetManager().getGameFont(shipColor, 15); // font size 15 pixels
        bigFont=Managers.getAssetManager().getGameFont(shipColor,25);

        score = 0;
        scorehealer = 0;
        multiplier = 0;
        level = 1;
        exp = 101;
        dead = false;
        this.type = type;


        texture = Managers.getAssetManager().getTexture(type);
        shipSprite = new Sprite(texture, texture.getWidth(), texture.getHeight());


        Texture texture2 = Managers.getAssetManager().getTexture("shield");
        shield = new Sprite(texture2, texture.getWidth(), texture.getHeight());
        shieldColor = Managers.getAssetManager().getColor("shield");

        Texture greenHptexture = Managers.getAssetManager().getTexture("hpbar");
        greenHp = new Sprite(greenHptexture, 100, 20);
        red = Color.RED;
        redHp = new Sprite(greenHptexture, 100, 20);
        green = Color.GREEN;
    }

    public Sprite getShipSprite() {
        return shipSprite;
    }

    public String getType() {
        return type;
    }

    public void shoot() {

        if (canShoot) {
            Managers.getBulletManager().addBullet(new Bullet(new Vector2(position), new Vector2(shootDir)));
            canShoot = false;
        }
    }

    public void handleHit(int damage) {
        hp -= damage;
        multiplier = 0;
        hitTime = 0.5f;
        if (hp < 1) {

            setDead();

            Managers.getGameManager().endGame();

            MenuScreen mainmenu = Managers.getScreenManager().getScreen("endgamemenu");
            Managers.getScreenManager().setScreen(mainmenu);
        }
    }

    public void handlePickedUp(Geom geom) {
        exp += geom.getLoot().getExperience();
        if (exp > 100 * level) {
            popUpTextPos = geom.getPosition();
            maxHp += 5;
            hp += 5;
            level++;
            exp = 0;
            Managers.getFloatingTextManager().addText(new FloatingText(font, "Level up", 5.0f, popUpTextPos));//////////////////////////////////////////////////////////////////
        }
        multiplier += geom.getLoot().getMultiplier();

        scorehealer += (geom.getLoot().getScorePoints()) * multiplier;
        score += (geom.getLoot().getScorePoints()) * multiplier;
        if (scorehealer > 50000) {
            Managers.getFloatingTextManager().addText(new FloatingText(font, "hp++", 5.0f, new Vector2(position)));
            hp++;
            scorehealer %= 50000;
        }

    }

    public void handlePickedUp(PowerUp pow) {
        POWERUPTYPE x = pow.getType();
        switch (x) {

            case NUKE:
                Managers.getpowerUpManager().activatePowerUp(pow);
                break;
            case PASSIVE:
                handlePassivePow(pow);
                Managers.getpowerUpManager().activatePowerUp(pow);
                break;
            case POWERDOWN:
                handleBadPow(pow);
                Managers.getpowerUpManager().activatePowerUp(pow);
        }

        Managers.getFloatingTextManager().addText(new FloatingText(font, pow.getText(), 5.0f, new Vector2(pow.getPosition())));

    }

    public void handlePassivePow(PowerUp pow) {
        Power_UpPassive p = (Power_UpPassive) pow;
        fireDelay /= p.getFireDelay();
        if (speed < 700) {
            speed += p.getSpeed();
        }
        maxHp += p.getExtraHp();
        hp += p.getExtraHp();
    }

    public void handleBadPow(PowerUp pow) {
        PowerDown_Stats p = (PowerDown_Stats) pow;
        if (fireDelay > 1.0f) {
            fireDelay /= p.getFireDelay();
        }
        if (speed > 200) {
            speed += p.getSpeed();
        }
        if (hp > 1) {
            maxHp += p.getExtraHp();
            hp += p.getExtraHp();
        }
    }

    public void handleEnemyCrash(Enemy enemy) {
        hp -= enemy.getHp();
        //kan even nie dood
        multiplier = 0;
        hitTime = 0.2f;
        if (hp < 1) {
            setDead();
            Managers.getGameManager().endGame();
            MenuScreen mainmenu = Managers.getScreenManager().getScreen("endgamemenu");
            Managers.getScreenManager().setScreen(mainmenu);
        }
        enemy.handleDead();
    }

    public Color getShipColor() {
        return shipColor;
    }


    public void nuke() {
        Managers.getBulletManager().clearAll();
    }

    @Override
    public void render(Batch batch) {

        //shipSprite.setColor(shipColor);

        shipSprite.setSize(50, 50);
        shipSprite.setOrigin(25, 25);
        shipSprite.setRotation(lookDir.angle());
        shipSprite.setPosition(position.x - 25, position.y - 25);
        shipSprite.draw(batch);

        greenHp.setColor(green);
        greenHp.setSize((int) (50 * (float) hp / maxHp), 5);
        greenHp.setPosition(position.x - 20, position.y + 30);
        redHp.setColor(red);
        redHp.setSize(50, 5);
        redHp.setPosition(position.x - 20, position.y + 30);
        redHp.draw(batch);
        greenHp.draw(batch);


        shield.setColor(shieldColor);
        shield.setSize(80, 80);
        shield.setOrigin(40, 40);
        shield.setPosition(position.x - 40, position.y - 40);
        shield.draw(batch);


        //niet in render


/*
        if (!popuptext.equals("")) {
            font.draw(batch, popuptext, popUpTextPos.x, popUpTextPos.y + popuptextTime);

            if (popuptextTime <= 0.01f) {
                popuptext = "";
                popuptextTime = 0.0f;
            }
            popuptextTime -= Gdx.graphics.getDeltaTime();
        }
        */
        bigFont.draw(batch, player.getName() + " score " + score + "  " + multiplier + "X" + "    LVL= " + level + "   HP " + hp + " I " + maxHp, player.getPlayerTextpos().x+30, player.getPlayerTextpos().y);

    }

    @Override
    public void update() {


        if (hitTime > 0.01f) {
            shipSprite.setColor(Color.BLACK);
            hitTime -= Gdx.graphics.getDeltaTime();
        } else {
            shipSprite.setColor(shipColor);
        }


        timer += Gdx.graphics.getDeltaTime();
        if (timer > fireDelay) {
            timer %= fireDelay;
            canShoot = true;
        }

        if (shootDir.len() > 0.01f) {
            shoot();
            lookDir = shootDir;
        } else if (moveDir.len() > 0.01f) {
            lookDir = moveDir;
        }

        Move();

        if (position.x < 0)
            position.x = GeoWars.ORIGINALWIDTH;
        if (position.x > GeoWars.ORIGINALWIDTH)
            position.x = 0;
        if (position.y < 0)
            position.y = GeoWars.ORIGINALHEIGHT;
        if (position.y > GeoWars.ORIGINALHEIGHT)
            position.y = 0;
    }

    private void Move() {
        position.mulAdd(moveDir, speed * Gdx.graphics.getDeltaTime());
    }

    public void setMoveDirection(Vector2 dir) {
        moveDir = dir;
    }

    public void setShootDirection(Vector2 dir) {
        shootDir = dir;
    }

    public void setDead() {
        dead = true;
    }


    public void upgradeSpeed(int speed)
    {
        this.speed+=speed;
    }
    public void heal(int health)
    {
        this.hp+=health;
    }
    public void upgradeMaxHp(int health)
    {
        this.maxHp+=health;
        this.hp=maxHp;
    }
    public void upgradeFireRate(float rate)
    {
        fireDelay-=rate;
    }
    public void dubbleMultiplier()
    {
        multiplier*=2;
    }

    public void reset() {
        position = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        score = 0;
        multiplier = 0;
        level = 0;
    }

}

