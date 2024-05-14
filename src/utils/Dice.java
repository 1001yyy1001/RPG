package utils;

import java.util.Random;

// 乱数を作成する便利なクラス
public class Dice {
	
	// 乱数インスタンスの生成
	private static Random r = new Random();

	// minからmaxまでの間で整数値のサイコロを振り、その値を戻り値とするメソッド
	public static int get(int min, int max) {
		return r.nextInt(max - min + 1) + min;
	}
}
