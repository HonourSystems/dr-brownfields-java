package drBrownFields;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class GameSessionListener implements HttpSessionListener{
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        Game game = new Game();
        boolean dealEnabled = true;
        HttpSession session = httpSessionEvent.getSession();
        session.setAttribute("game",game);
        Boolean sessionDrawVisible = (Boolean)session.getAttribute("dealEnabled");
        session.setAttribute("dealEnabled", dealEnabled);
        session.setAttribute("stake",game.getStake());
        session.setAttribute("win",game.getWin());
        session.setAttribute("bet",game.getBet());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
