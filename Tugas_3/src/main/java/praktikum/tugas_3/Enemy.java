/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package praktikum.tugas_3;

/**
 *
 * @author ROCKY
 */
public class Enemy {
    private int hp, dmg, reward, posx, posy;
    private char jenis;

    public Enemy(int hp, int dmg, int reward, int posx, int posy, char jenis) {
        this.hp = hp;
        this.dmg = dmg;
        this.reward = reward;
        this.posx = posx;
        this.posy = posy;
        this.jenis = jenis;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public char getJenis() {
        return jenis;
    }

    public void setJenis(char jenis) {
        this.jenis = jenis;
    }
    
}
