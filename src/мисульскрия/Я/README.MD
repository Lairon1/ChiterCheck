# CheaterCheck
## _Плагин для проверок игроков на читы!_

[![Build Status](https://prodma.ru/wp-content/uploads/2009/12/%D0%BA%D0%BD%D0%BE%D0%BF%D0%BA%D0%B0-%D1%81%D0%BA%D0%B0%D1%87%D0%B0%D1%82%D1%8C2.png)](https://drive.google.com/u/1/uc?id=1iOK_zLCExSVM53DMuSlMWCGrNcj2HxFN&export=download)

Подробнее про плагин и его использованее вы можете прочитать тут ( http://rubukkit.org/ )



## API Плагина
#### Для начало нам нужно получить нашь плагин.
```sh
CheaterCheck cheaterCheck = (CheaterCheck) Bukkit.getPluginManager().getPlugin("CheaterCheck");
```
#### После чего мы можем получить доступ к менеджерам плагина.
```sh
Check check = cheaterCheck.getCheck(); // МЕНЕДЖЕР ПРОВЕРОК.
CheckLogger сheckLogger = cheaterCheck.getCheckLogger(); // МЕНЕДЖЕР ЛОГОВ.
BossBarMeneger bossBarMeneger = cheaterCheck.getBossBarMeneger(); // МЕНЕДЖЕР БОССБАРОВ.
```


#### Подробнее про менеджер проверок.
```sh
check.confess(suspect); //ИСКУСТВЕННО ПРИЗНАТСЯ В ЧИТАХ ПРИ ПРОВЕРКЕ.
Player moderator = check.getModerator(suspect); // ПОЛУЧИТЬ ИГРОКА КОТОРЫЙ ВЫЗВАЛ НА ПРОВЕРКУ ПОДОЗРЕВАЕМОГО.
check.setModerator(suspect, moderator); // УСТАНОВИТЬ МОДЕРАТОРА ДЛЯ ПОДОЗРЕВАЕМОГО.
int timeInCheck = check.getTimeCheck(suspect); // ПОЛУЧИТЬ ВРЕМЯ КОТОРОЕ ОСТАЛОСЬ У ИГРОКА .
check.setTimeCheck(suspect, timeInCheck); // УСТАНОВИТЬ ВРЕМЯ ПРОВЕРКИ.
check.isChecks(suspect); //ПОЛУЧИТЬ true ИЛИ false, НАХОДИТСЯ ЛИ ИГРОК НА ПРОВЕРКЕ ИЛИ НЕТ.
check.start(suspect, moderator); // ЗАПУСТИТЬ ПРОВЕРКУ ДЛЯ ИГРОКА .
check.stop(suspect, moderator); // ОСТАНОВИТЬ ПРОВЕРКУ.
check.suspectLeave(suspect); // ИСКУСТВЕННО ВЫЗВАТЬ МЕТОД ПРИ КОТОРОМ ИГРОК ОТКАЗЫВАЕТСЯ ОТ ПРОВЕРКИ.
check.getSuspects(); // ПОЛУЧИТ МАССИВ ИГРОКОВ КОТОРЫЕ НАХОДЯТСЯ НА ПРОВЕРКЕ.
check.getModerators(); //ПОЛУЧИТЬ МАССИВ МОДЕРАТОРОВ У КОТОРЫХ ЕСТЬ ХОТЯБЫ 1 АКТИВНАЯ ПРОВЕРКА.
check.connect(suspect, "дискорд/скайп"); // ИСКУСТВЕННО ОТПРАВИТЬ ДАННЫЕ ДЛЯ СВЯЗИ МОДЕРАТОРУ.
```
#### Подробнее про менеджер логов.
```sh
сheckLogger.addLog("НИК НА КОГО ЗАПИСЫВАЕМ ЛОГ", "НИК МОДЕРАТОРА", "НИК SUSPECT'a", "НАЗВАНИЕ ЛОГА"); // ЗАПИСАТЬ ЛОГ.
сheckLogger.getLoggs("NICK"); //ПОЛУЧИТЬ СПИСОК ЛОГОВ ПО НИКНЕЙМУ.
```
#### Подробнее про менеджер боссбаров.
```sh
BossBar bar = bossBarMeneger.getSuspectBossBar(suspect); // ПОЛУЧИТЬ БОСС БАР ПО ИГРОКУ.
bossBarMeneger.showSuspectBossBar(suspect); // СОЗДАТЬ НОВЫЙ БОСС БАР ДЛЯ ИГРОКА.
bossBarMeneger.removeSuspectBossBar(suspect); // УДАЛИТЬ БОСС БАР КОТОРЫЙ ПРЕНАДЛЕЖИТ ИГРОКУ.	
```
#### Так же есть ивенты.
```sh
PlayerCheckStartEvent // вызывается когда на игрока накладывают проверку.
PlayerCheckStopEvent // вызывается когда у игрока останавливают проверку.
PlayerCheckConfessEvent // вызывается когда игрок признается в читах.
PlayerCheckQuitEvent // вызывается когда игрок выходит с сервера во время проверки или же когда кончается время.
```
