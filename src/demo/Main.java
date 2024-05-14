package demo;

import java.util.ArrayList;
import java.util.List;

import bases.Human;
import bases.Monster;
import humans.Brave;
import humans.Fighter;
import humans.Wizard;
import monsters.Dragon;
import monsters.Oak;
import monsters.Slime;
import utils.Dice;

public class Main {
    public static void main(String[] args) {
        System.out.println("★★ ==== 戦いの開始だ！！ ==== ★★");

        Human brave = new Brave("沖田総司", "剣");
        Human fighter = new Fighter("金太郎", "斧");
        Human wizard = new Wizard("安倍晴明", "魔法");

        List<Human> humans = new ArrayList<>();
        humans.add(brave);
        humans.add(fighter);
        humans.add(wizard);

        Monster slime = new Slime("キングスライム", "体当たり");
        Monster oak = new Oak("オークキング", "槍");
        Monster dragon = new Dragon("紅龍", "炎");

        List<Monster> monsters = new ArrayList<>();
        monsters.add(slime);
        monsters.add(oak);
        monsters.add(dragon);

        showGroupInfos(humans, monsters);

        int count = 1;
        while (!humans.isEmpty() && !monsters.isEmpty()) {
            System.out.printf("\n★ 第%d回戦 ==========\n", count);

            Human selectedHuman = choiceHuman(humans);
            Monster selectedMonster = choiceMonster(monsters);

            if (selectedHuman != null && selectedMonster != null) {
                selectedHuman.attack(selectedMonster);
                if (selectedMonster.getHp() <= 0) {
                    System.out.println(selectedMonster.getName() + "が倒れた！");
                    monsters.remove(selectedMonster);
                }
            }

            if (monsters.isEmpty()) {
                System.out.println("人間グループの勝利！");
                break;
            }

            selectedMonster = choiceMonster(monsters);
            selectedHuman = choiceHuman(humans);

            if (selectedMonster != null && selectedHuman != null) {
                selectedMonster.attack(selectedHuman);
                if (selectedHuman.getHp() <= 0) {
                    System.out.println(selectedHuman.getName() + "が倒れた！");
                    humans.remove(selectedHuman);
                }
            }

            if (humans.isEmpty()) {
                System.out.println("モンスターグループの勝利！");
                break;
            }

            showGroupInfos(humans, monsters);
            count++;
        }

        showGroupInfos(humans, monsters);
        System.out.println("★★ ==== 戦いが終了しました！ ==== ★★");
    }

    public static Human choiceHuman(List<Human> humans) {
        if (humans.isEmpty()) return null;
        int index = Dice.get(0, humans.size() - 1);
        Human selected = humans.get(index);
        System.out.printf("人間グループから「%s」のお出ましだ！\n", selected.getName());
        return selected;
    }

    public static Monster choiceMonster(List<Monster> monsters) {
        if (monsters.isEmpty()) return null;
        int index = Dice.get(0, monsters.size() - 1);
        Monster selected = monsters.get(index);
        System.out.printf("モンスターグループから「%s」のお出ましだ！\n", selected.getName());
        return selected;
    }

    public static void showGroupInfos(List<Human> humans, List<Monster> monsters) {
        System.out.println("\n## === グループ情報 === ##");
        System.out.printf("# [人間グループ]: %d人\n", humans.size());
        for (Human human : humans) {
            System.out.println(human);
        }

        System.out.printf("\n# [モンスターグループ]: %d人\n", monsters.size());
        for (Monster monster : monsters) {
            System.out.println(monster);
        }
    }
}