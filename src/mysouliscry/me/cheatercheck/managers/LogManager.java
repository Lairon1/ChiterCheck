package mysouliscry.me.cheatercheck.managers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mysouliscry.me.cheatercheck.CheaterCheck;

/**
 * @author MySoulIsCry
 *
 */


public class LogManager {
	private CheaterCheck main;
	public LogManager(CheaterCheck main) {
		this.main = main;
	}

	public void addLog(String nameLog, String moderatorNick, String suspectNick, String reason) {
		ArrayList<String> logsSuspect = (ArrayList<String>) main.getLog().getList(nameLog);
		if(logsSuspect == null) logsSuspect = new ArrayList<String>();	
		String format = new SimpleDateFormat("d.MM.yy H:m:s").format(new Date());
		String logSuspect =  "§7(§c" + reason + "§7)\nДата: §a" + format + "§7\nПодозреваемый: §a" + suspectNick + "§7\nМодератор: §a" + moderatorNick;
		logsSuspect.add(logSuspect);
		main.getLog().set(nameLog, logsSuspect);
		try {
			main.getLog().save(main.logsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<String> getLoggs(String player) {
		ArrayList<String> listLogs = (ArrayList<String>) main.getLog().getList(player);
		if(listLogs == null) {
			listLogs = new ArrayList<String>();
			listLogs.add("Пусто");
		}	
		return listLogs;	
	}


}
