package praktikum.tugas_3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        int inp;
        do {
            System.out.println("===Bajak Laut===");
            System.out.println("1. Play");
            System.out.println("0. Exit");
            System.out.println(">> ");
            inp = Integer.parseInt(sc.nextLine());
            
            if (inp == 1) {
                char[][] laut = new char[12][22];
                int[] p = new int[2];
                int[][] pl = new int[3][2];
                boolean[] chest = new boolean[3];
                chest[0] = true;
                chest[1] = true;
                chest[2] = true;
                int chest_sold = 0;
                ArrayList<Enemy> musuh = new ArrayList<>();
                String ctrl;
                for (int i = 0; i < 12; i++) {
                    for (int j = 0; j < 22; j++) {
                        if ((i == 0 || i == 11) && (j == 0 || j == 21)) {
                            laut[i][j] = '+';
                        }else if (i == 0 || i == 11) {
                            laut[i][j] = '-';
                        }else if (j == 0 || j == 21) {
                            laut[i][j] = '|';
                        }else{
                            laut[i][j] = '~';
                        }
                    }
                }
                Player ply = new Player(rd.nextInt(20)+1, rd.nextInt(10)+1);
                laut[ply.getPosy()][ply.getPosx()] = ply.getSimbol();
                do {
                    p[0] = rd.nextInt(20)+1;
                    p[1] = rd.nextInt(10)+1;
                    if (laut[p[1]][p[0]] == '~') {
                        break;
                    }
                } while (true);
                laut[p[1]][p[0]] = 'P';
                for (int i = 0; i < 3; i++) {
                    do {
                        pl[i][0] = rd.nextInt(20)+1;
                        pl[i][1] = rd.nextInt(10)+1;
                        if (laut[pl[i][1]][pl[i][0]] == '~') {
                            break;
                        }
                    } while (true);
                    laut[pl[i][1]][pl[i][0]] = '#';
                }
                for (int i = 0; i < 5; i++) {
                    int x,y;
                    do {
                        x = rd.nextInt(20)+1;
                        y = rd.nextInt(10)+1;
                        if (laut[y][x] == '~') {
                            break;
                        }
                    } while (true);
                    if (i < 2) {
                        musuh.add(new Enemy(50, 10, 20, x, y, 'S'));
                        laut[musuh.get(i).getPosy()][musuh.get(i).getPosx()] = musuh.get(i).getJenis();
                    }else if (i < 4) {
                        musuh.add(new Enemy(80, 20, 50, x, y, 'M'));
                        laut[musuh.get(i).getPosy()][musuh.get(i).getPosx()] = musuh.get(i).getJenis();
                    }else{
                        musuh.add(new Enemy(150, 40, 100, x, y, 'L'));
                        laut[musuh.get(i).getPosy()][musuh.get(i).getPosx()] = musuh.get(i).getJenis();
                    }
                }
                
                // playing game
                do {
                    for (int i = 0; i < 3; i++) {
                        if (laut[pl[i][1]][pl[i][0]] == '~') {
                            laut[pl[i][1]][pl[i][0]] = '#';
                        }
                    }
                    for (int i = 0; i < 12; i++) {
                        for (int j = 0; j < 22; j++) {
                            System.out.print(laut[i][j]);
                        }
                        System.out.println();
                    }
                    System.out.println("Gold : " + ply.getGold());
                    System.out.println(">> ");
                    ctrl = sc.nextLine();
                    
                    boolean in_pl = false, fg_e = false, in_p = false;
                    int cpl = -1, ce = -1, e_move = 1;
                    if (ctrl.equalsIgnoreCase("w")) {
                        if (laut[ply.getPosy()-1][ply.getPosx()] == '~') {
                            laut[ply.getPosy()][ply.getPosx()] = '~';
                            ply.setPosy(ply.getPosy()-1);
                            laut[ply.getPosy()][ply.getPosx()] = ply.getSimbol();
                        }else if (laut[ply.getPosy()-1][ply.getPosx()] == '#') {
                            in_pl = true;
                            for (int i = 0; i < 3; i++) {
                                if (pl[i][0] == ply.getPosx() && pl[i][1] == ply.getPosy()-1) {
                                    cpl = i;
                                    break;
                                }
                            }
                        }else if (laut[ply.getPosy()-1][ply.getPosx()] == 'S' || laut[ply.getPosy()-1][ply.getPosx()] == 'M' || laut[ply.getPosy()-1][ply.getPosx()] == 'L') {
                            fg_e = true;
                            for (int i = 0; i < musuh.size(); i++) {
                                Enemy e = musuh.get(i);
                                if (e.getPosx() == ply.getPosx() && e.getPosy() == ply.getPosy()-1) {
                                    ce = i;
                                    e_move = 0;
                                    break;
                                }
                            }
                        }else if (laut[ply.getPosy()-1][ply.getPosx()] == 'P') {
                            in_p = true;
                        }
                    }else if (ctrl.equalsIgnoreCase("a")) {
                        if (laut[ply.getPosy()][ply.getPosx()-1] == '~') {
                            laut[ply.getPosy()][ply.getPosx()] = '~';
                            ply.setPosx(ply.getPosx()-1);
                            laut[ply.getPosy()][ply.getPosx()] = ply.getSimbol();
                        }else if (laut[ply.getPosy()][ply.getPosx()-1] == '#') {
                            in_pl = true;
                            for (int i = 0; i < 3; i++) {
                                if (pl[i][0] == ply.getPosx()-1 && pl[i][1] == ply.getPosy()) {
                                    cpl = i;
                                    break;
                                }
                            }
                        }else if (laut[ply.getPosy()][ply.getPosx()-1] == 'S' || laut[ply.getPosy()][ply.getPosx()-1] == 'M' || laut[ply.getPosy()][ply.getPosx()-1] == 'L') {
                            fg_e = true;
                            for (int i = 0; i < musuh.size(); i++) {
                                Enemy e = musuh.get(i);
                                if (e.getPosx() == ply.getPosx()-1 && e.getPosy() == ply.getPosy()) {
                                    ce = i;
                                    e_move = 0;
                                    break;
                                }
                            }
                        }else if (laut[ply.getPosy()][ply.getPosx()-1] == 'P') {
                            in_p = true;
                        }
                    }else if (ctrl.equalsIgnoreCase("s")) {
                        if (laut[ply.getPosy()+1][ply.getPosx()] == '~') {
                            laut[ply.getPosy()][ply.getPosx()] = '~';
                            ply.setPosy(ply.getPosy()+1);
                            laut[ply.getPosy()][ply.getPosx()] = ply.getSimbol();
                        }else if (laut[ply.getPosy()+1][ply.getPosx()] == '#') {
                            in_pl = true;
                            for (int i = 0; i < 3; i++) {
                                if (pl[i][0] == ply.getPosx() && pl[i][1] == ply.getPosy()+1) {
                                    cpl = i;
                                    break;
                                }
                            }
                        }else if (laut[ply.getPosy()+1][ply.getPosx()] == 'S' || laut[ply.getPosy()+1][ply.getPosx()] == 'M' || laut[ply.getPosy()+1][ply.getPosx()] == 'L') {
                            fg_e = true;
                            for (int i = 0; i < musuh.size(); i++) {
                                Enemy e = musuh.get(i);
                                if (e.getPosx() == ply.getPosx() && e.getPosy() == ply.getPosy()+1) {
                                    ce = i;
                                    e_move = 0;
                                    break;
                                }
                            }
                        }else if (laut[ply.getPosy()+1][ply.getPosx()] == 'P') {
                            in_p = true;
                        }
                    }else if (ctrl.equalsIgnoreCase("d")) {
                        if (laut[ply.getPosy()][ply.getPosx()+1] == '~') {
                            laut[ply.getPosy()][ply.getPosx()] = '~';
                            ply.setPosx(ply.getPosx()+1);
                            laut[ply.getPosy()][ply.getPosx()] = ply.getSimbol();
                        }else if (laut[ply.getPosy()][ply.getPosx()+1] == '#') {
                            in_pl = true;
                            for (int i = 0; i < 3; i++) {
                                if (pl[i][0] == ply.getPosx()+1 && pl[i][1] == ply.getPosy()) {
                                    cpl = i;
                                    break;
                                }
                            }
                        }else if (laut[ply.getPosy()][ply.getPosx()+1] == 'S' || laut[ply.getPosy()][ply.getPosx()+1] == 'M' || laut[ply.getPosy()][ply.getPosx()+1] == 'L') {
                            fg_e = true;
                            for (int i = 0; i < musuh.size(); i++) {
                                Enemy e = musuh.get(i);
                                if (e.getPosx() == ply.getPosx()+1 && e.getPosy() == ply.getPosy()) {
                                    ce = i;
                                    e_move = 0;
                                    break;
                                }
                            }
                        }else if (laut[ply.getPosy()][ply.getPosx()+1] == 'P') {
                            in_p = true;
                        }
                    }else if (ctrl.equals("cheat")) {
                        ply.setHp(999);
                        ply.setDmg(999);
                        ply.setRepair(999);
                        e_move = 0;
                    }else{
                        e_move = 0;
                    }
                    
                    // dalam pulau
                    if (in_pl) {
                        e_move = 0;
                        if (chest[cpl]) {
                            do {
                                System.out.println("##### Island #####");
                                System.out.println("Finding Treasure...");
                                if (rd.nextInt(5) < 2) {
                                    System.out.println("You found a chest!");
                                    ply.setChest(ply.getChest()+1);
                                    chest[cpl] = false;
                                    String temp = sc.nextLine();
                                    break;
                                }else{
                                    System.out.println("You found nothing :(");
                                    e_move++;
                                    System.out.println("Search again? [Y/N]");
                                    if (sc.nextLine().equalsIgnoreCase("n")) {
                                        break;
                                    }
                                }
                            } while (true);
                        }
                    }
                    
                    // dalam pos
                    if(in_p){
                        e_move = 0;
                        int inp2;
                        do {
                            System.out.println("[][] Sea Post [][]");
                            System.out.println("1. Sell chest");
                            System.out.println("2. Upgrade Ship");
                            System.out.println("0. Exit");
                            System.out.println(">> ");
                            inp2 = Integer.parseInt(sc.nextLine());
                            
                            if (inp2 == 1) {
                                System.out.println("How much chest want to sell : ");
                                int tmp = Integer.parseInt(sc.nextLine());
                                if (ply.getChest() >= tmp) {
                                    ply.setChest(ply.getChest() - tmp);
                                    ply.setGold(ply.getGold() + (tmp * 100));
                                    System.out.println("You got " + tmp*100 + " Gold");
                                    chest_sold += tmp;
                                    if (chest_sold == 3) {
                                        break;
                                    }
                                }else{
                                    System.out.println("You don't have enough chest!");
                                }
                            }else if (inp2 == 2) {
                                System.out.println("[][] Upgrade [][]");
                                System.out.println("1. Body   @40G");
                                System.out.println("2. Connon @50G");
                                System.out.println("3. Tools  @30G");
                                System.out.println(">> ");
                                int inp3 = Integer.parseInt(sc.nextLine());
                                
                                if (inp3 == 1) {
                                    if (ply.getGold() >= 40) {
                                        ply.setHp(ply.getHp() + 40);
                                        System.out.println("Upgrade body success!");
                                        ply.setGold(ply.getGold() - 40);
                                    }else{
                                        System.out.println("You don't have enough gold!");
                                    }
                                }else if (inp3 == 2) {
                                    if (ply.getGold() >= 50) {
                                        ply.setDmg(ply.getDmg() + 20);
                                        System.out.println("Upgrade cannon success!");
                                        ply.setGold(ply.getGold() - 50);
                                    }else{
                                        System.out.println("You don't have enough gold!");
                                    }
                                }else if (inp3 == 3) {
                                    if (ply.getGold() >= 30) {
                                        ply.setRepair(ply.getRepair() + 10);
                                        System.out.println("Upgrade tools success!");
                                    }else{
                                        System.out.println("You don't have enough gold!");
                                        ply.setGold(ply.getGold() - 30);
                                    }
                                }
                            }
                        } while (inp2 != 0);
                        if (chest_sold == 3) {
                            break;
                        }
                    }
                    
                    // musuh jalan
                    for (int i = 0; i < e_move; i++) {
                        for (int j = 0; j < musuh.size(); j++) {
                            Enemy e = musuh.get(j);
                            int ex = e.getPosx(), ey = e.getPosy();
                            int px = ply.getPosx(), py = ply.getPosy();
                            if (ex > px && ey < py) {
                                switch (rd.nextInt(2)) {
                                    case 0:
                                        if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosx(ex-1);
                                            laut[ey][e.getPosx()] = e.getJenis();
                                        }else if(laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosy(ey+1);
                                            laut[e.getPosy()][ex] = e.getJenis();
                                        }else{
                                            switch (rd.nextInt(2)) {
                                                case 0:
                                                    if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex+1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }else if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey-1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }
                                                    break;
                                                case 1:
                                                    if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey-1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }else if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex+1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1:
                                        if (laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosy(ey+1);
                                            laut[e.getPosy()][ex] = e.getJenis();
                                        }else if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosx(ex-1);
                                            laut[ey][e.getPosx()] = e.getJenis();
                                        }else{
                                            switch (rd.nextInt(2)) {
                                                case 0:
                                                    if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex+1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }else if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey-1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }
                                                    break;
                                                case 1:
                                                    if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey-1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }else if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex+1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                }
                            }else if (ex > px && ey > py) {
                                switch (rd.nextInt(2)) {
                                    case 0:
                                        if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosy(ey-1);
                                            laut[e.getPosy()][ex] = e.getJenis();
                                        }else if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosx(ex-1);
                                            laut[ey][e.getPosx()] = e.getJenis();
                                        }else{
                                            switch (rd.nextInt(2)) {
                                                case 0:
                                                    if (laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey+1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }else if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex+1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }else{

                                                    }
                                                    break;
                                                case 1:
                                                    if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex+1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }else if (laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey+1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1:
                                        if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosx(ex-1);
                                            laut[ey][e.getPosx()] = e.getJenis();
                                        }else if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosy(ey-1);
                                            laut[e.getPosy()][ex] = e.getJenis();
                                        }else{
                                            switch (rd.nextInt(2)) {
                                                case 0:
                                                    if (laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey+1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }else if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex+1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }else{

                                                    }
                                                    break;
                                                case 1:
                                                    if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex+1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }else if (laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey+1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                }
                            }else if (ex < px && ey > py) {
                                switch (rd.nextInt(2)) {
                                    case 0:
                                        if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosx(ex+1);
                                            laut[ey][e.getPosx()] = e.getJenis();
                                        }else if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosy(ey-1);
                                            laut[e.getPosy()][ex] = e.getJenis();
                                        }else{
                                            switch (rd.nextInt(2)) {
                                                case 0:
                                                    if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex-1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }else if(laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey+1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }else{

                                                    }
                                                    break;
                                                case 1:
                                                    if (laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey+1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }else if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex-1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1:
                                        if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosy(ey-1);
                                            laut[e.getPosy()][ex] = e.getJenis();
                                        }else if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosx(ex+1);
                                            laut[ey][e.getPosx()] = e.getJenis();
                                        }else{
                                            switch (rd.nextInt(2)) {
                                                case 0:
                                                    if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex-1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }else if(laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey+1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }else{

                                                    }
                                                    break;
                                                case 1:
                                                    if (laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey+1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }else if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex-1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                }
                            }else if (ex < px && ey < py) {
                                switch (rd.nextInt(2)) {
                                    case 0:
                                        if (laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosy(ey+1);
                                            laut[e.getPosy()][ex] = e.getJenis();
                                        }else if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosx(ex+1);
                                            laut[ey][e.getPosx()] = e.getJenis();
                                        }else{
                                            switch (rd.nextInt(2)) {
                                                case 0:
                                                    if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey-1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }else if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex-1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }
                                                    break;
                                                case 1:
                                                    if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex-1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }else if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey-1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1:
                                        if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosx(ex+1);
                                            laut[ey][e.getPosx()] = e.getJenis();
                                        }else if (laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                            laut[ey][ex] = '~';
                                            e.setPosy(ey+1);
                                            laut[e.getPosy()][ex] = e.getJenis();
                                        }else{
                                            switch (rd.nextInt(2)) {
                                                case 0:
                                                    if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey-1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }else if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex-1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }
                                                    break;
                                                case 1:
                                                    if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosx(ex-1);
                                                        laut[ey][e.getPosx()] = e.getJenis();
                                                    }else if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                        laut[ey][ex] = '~';
                                                        e.setPosy(ey-1);
                                                        laut[e.getPosy()][ex] = e.getJenis();
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                }
                            }else if (ey < py) {
                                if (laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                    laut[ey][ex] = '~';
                                    e.setPosy(ey+1);
                                    laut[e.getPosy()][ex] = e.getJenis();
                                }else if (laut[ey+1][ex] == 'X') {
                                    fg_e = true;
                                    ce = j;
                                }else{
                                    switch(rd.nextInt(2)) {
                                        case 0:
                                            if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosx(ex+1);
                                                laut[ey][e.getPosx()] = e.getJenis();
                                            }else if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosx(ex-1);
                                                laut[ey][e.getPosx()] = e.getJenis();
                                            }else if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosy(ey-1);
                                                laut[e.getPosy()][ex] = e.getJenis();
                                            }
                                            break;
                                        case 1:
                                            if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosx(ex-1);
                                                laut[ey][e.getPosx()] = e.getJenis();
                                            }else if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosx(ex+1);
                                                laut[ey][e.getPosx()] = e.getJenis();
                                            }else if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosy(ey-1);
                                                laut[e.getPosy()][ex] = e.getJenis();
                                            }
                                            break;
                                    }
                                }
                            }else if (ex > px) {
                                if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                    laut[ey][ex] = '~';
                                    e.setPosx(ex-1);
                                    laut[ey][e.getPosx()] = e.getJenis();
                                }else if (laut[ey][ex-1] == 'X') {
                                    fg_e = true;
                                    ce = j;
                                }else{
                                    switch(rd.nextInt(2)) {
                                        case 0:
                                            if (laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosy(ey+1);
                                                laut[e.getPosy()][ex] = e.getJenis();
                                            }else if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosy(ey-1);
                                                laut[e.getPosy()][ex] = e.getJenis();
                                            }else if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosx(ex+1);
                                                laut[ey][e.getPosx()] = e.getJenis();
                                            }
                                            break;
                                        case 1:
                                            if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosy(ey-1);
                                                laut[e.getPosy()][ex] = e.getJenis();
                                            }else if (laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosy(ey+1);
                                                laut[e.getPosy()][ex] = e.getJenis();
                                            }else if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosx(ex+1);
                                                laut[ey][e.getPosx()] = e.getJenis();
                                            }
                                            break;
                                    }
                                }
                            }else if (ey > py) {
                                if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                    laut[ey][ex] = '~';
                                    e.setPosy(ey-1);
                                    laut[e.getPosy()][ex] = e.getJenis();
                                }else if (laut[ey-1][ex] == 'X') {
                                    fg_e = true;
                                    ce = j;
                                }else{
                                    switch(rd.nextInt(2)) {
                                        case 1:
                                            if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosx(ex+1);
                                                laut[ey][e.getPosx()] = e.getJenis();
                                            }else if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosx(ex-1);
                                                laut[ey][e.getPosx()] = e.getJenis();
                                            }else if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosy(ey-1);
                                                laut[e.getPosy()][ex] = e.getJenis();
                                            }
                                            break;
                                        case 0:
                                            if (laut[ey][ex-1] == '~' || laut[ey][ex-1] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosx(ex-1);
                                                laut[ey][e.getPosx()] = e.getJenis();
                                            }else if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosx(ex+1);
                                                laut[ey][e.getPosx()] = e.getJenis();
                                            }else if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosy(ey-1);
                                                laut[e.getPosy()][ex] = e.getJenis();
                                            }
                                            break;
                                    }
                                }
                            }else if (ex < px) {
                                if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                    laut[ey][ex] = '~';
                                    e.setPosx(ex+1);
                                    laut[ey][e.getPosx()] = e.getJenis();
                                }else if (laut[ey][ex+1] == 'X') {
                                    fg_e = true;
                                    ce = j;
                                }else{
                                    switch(rd.nextInt(2)) {
                                        case 1:
                                            if (laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosy(ey+1);
                                                laut[e.getPosy()][ex] = e.getJenis();
                                            }else if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosy(ey-1);
                                                laut[e.getPosy()][ex] = e.getJenis();
                                            }else if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosx(ex+1);
                                                laut[ey][e.getPosx()] = e.getJenis();
                                            }
                                            break;
                                        case 0:
                                            if (laut[ey-1][ex] == '~' || laut[ey-1][ex] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosy(ey-1);
                                                laut[e.getPosy()][ex] = e.getJenis();
                                            }else if (laut[ey+1][ex] == '~' || laut[ey+1][ex] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosy(ey+1);
                                                laut[e.getPosy()][ex] = e.getJenis();
                                            }else if (laut[ey][ex+1] == '~' || laut[ey][ex+1] == '#') {
                                                laut[ey][ex] = '~';
                                                e.setPosx(ex+1);
                                                laut[ey][e.getPosx()] = e.getJenis();
                                            }
                                            break;
                                    }
                                }
                            }
                        }
                    }
                    
                    // dalam battle
                    if (fg_e) {
                        Enemy e = musuh.get(ce);
                        int hpe = e.getHp(), hpp = ply.getHp(), inp3;
                        boolean isMenang;
                        do {
                            System.out.println("~~~~~~ Battle ~~~~~~");
                            System.out.println("Enemy [" + e.getJenis() +"]   You");
                            System.out.print("HP  : " + hpe);
                            for (int i = 0; i < 6-Integer.toString(hpe).length(); i++) {
                                System.out.print(" ");
                            }
                            System.out.println("HP  : " + hpp);
                            System.out.println("DMG : " + e.getDmg() + "    DMG : " + ply.getDmg());
                            System.out.println("======================");
                            System.out.println("1. Fire");
                            System.out.println("2. Repair");
                            System.out.println("3. Escape");
                            System.out.println(">> ");
                            inp3 = Integer.parseInt(sc.nextLine());
                            
                            if (inp3 == 1) {
                                System.out.println("You fired at enemy! (-" + ply.getDmg() + ")");
                                hpe -= ply.getDmg();
                                System.out.println("Enemy fired at you! (-" + e.getDmg() + ")");
                                hpp -= e.getDmg();
                            }else if (inp3 == 2) {
                                System.out.println("You fixed yout ship! (+" + ply.getRepair() + ")");
                                if (hpp + ply.getRepair() <= ply.getHp()) {
                                    hpp += ply.getRepair();
                                }else{
                                    hpp = ply.getHp();
                                }
                                System.out.println("Enemy fired at you! (-" + e.getDmg() + ")");
                                hpp -= e.getDmg();
                            }else if (inp3 == 3) {
                                int ex,ey;
                                if (e.getJenis() == 'S') {
                                    if (rd.nextInt(10) == 0) {
                                        do {
                                            ex = rd.nextInt(20)+1;
                                            ey = rd.nextInt(10)+1;
                                            
                                            if (laut[ey][ex] == '~') {
                                                break;
                                            }
                                        } while (true);
                                        laut[e.getPosy()][e.getPosx()] = '~';
                                        e.setPosx(ex);
                                        e.setPosy(ey);
                                        laut[e.getPosy()][e.getPosx()] = e.getJenis();
                                        isMenang = true;
                                        break;
                                    }else{
                                        System.out.println("Enemy fired at you! (-" + e.getDmg() + ")");
                                        hpp -= e.getDmg();
                                    }
                                }else if (e.getJenis() == 'M') {
                                    if (rd.nextInt(5) == 0) {
                                        do {
                                            ex = rd.nextInt(20)+1;
                                            ey = rd.nextInt(10)+1;
                                            
                                            if (laut[ey][ex] == '~') {
                                                break;
                                            }
                                        } while (true);
                                        laut[e.getPosy()][e.getPosx()] = '~';
                                        e.setPosx(ex);
                                        e.setPosy(ey);
                                        laut[e.getPosy()][e.getPosx()] = e.getJenis();
                                        isMenang = true;
                                        break;
                                    }else{
                                        System.out.println("Enemy fired at you! (-" + e.getDmg() + ")");
                                        hpp -= e.getDmg();
                                    }
                                }else if (e.getJenis() == 'L') {
                                    if (rd.nextInt(5) < 2) {
                                        do {
                                            ex = rd.nextInt(20)+1;
                                            ey = rd.nextInt(10)+1;
                                            
                                            if (laut[ey][ex] == '~') {
                                                break;
                                            }
                                        } while (true);
                                        laut[e.getPosy()][e.getPosx()] = '~';
                                        e.setPosx(ex);
                                        e.setPosy(ey);
                                        laut[e.getPosy()][e.getPosx()] = e.getJenis();
                                        isMenang = true;
                                        break;
                                    }else{
                                        System.out.println("Enemy fired at you! (-" + e.getDmg() + ")");
                                        hpp -= e.getDmg();
                                    }
                                }
                            }
                            
                            if (hpp <= 0) {
                                System.out.println("You Lose!");
                                isMenang = false;
                                break;
                            }
                            if (hpe <= 0) {
                                System.out.println("You Won!");
                                isMenang = true;
                                ply.setGold(ply.getGold() + e.getReward());
                                laut[e.getPosy()][e.getPosx()] = '~';
                                musuh.remove((int)ce);
                                int ex,ey;
                                do {
                                    ex = rd.nextInt(20)+1;
                                    ey = rd.nextInt(10)+1;
                                    
                                    if (laut[ey][ex] == '~') {
                                        break;
                                    }
                                } while (true);
                                switch (rd.nextInt(3)) {
                                    case 0:
                                        musuh.add(new Enemy(50, 10, 20, ex, ey, 'S'));
                                        laut[ey][ex] = 'S';
                                        break;
                                    case 1:
                                        musuh.add(new Enemy(80, 20, 50, ex, ey, 'M'));
                                        laut[ey][ex] = 'M';
                                        break;
                                    case 2:
                                        musuh.add(new Enemy(150, 40, 100, ex, ey, 'L'));
                                        laut[ey][ex] = 'L';
                                        break;
                                }
                                break;
                            }
                        } while (true);
                        if (!isMenang) {
                            break;
                        }
                    }
                } while (true);
                System.out.println("Game Over!");
                String temp = sc.nextLine();
            }
        } while (inp != 0);
    }
    
}
