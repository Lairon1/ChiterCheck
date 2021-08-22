package mysouliscry.me.cheatercheck.util;

public class util {

	public util() {
		// TODO Auto-generated constructor stub
	}
	public static String timeToString(int seccond) {
		int min = seccond / 60 % 60, sec = seccond / 1 % 60;
		return String.format("%02d:%02d", min , sec);
	}
}
