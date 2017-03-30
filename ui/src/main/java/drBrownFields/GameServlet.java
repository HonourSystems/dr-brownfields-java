package drBrownFields;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: michael
 * Date: 28/07/13
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameServlet extends HttpServlet {
//    private HtmlImage[] cardImages;
    private List<String> cardImages = new ArrayList<String>();
//    private HtmlInputCheckBox[] checkboxes;
    private Boolean dealEnabled = true;
    private Game game;
    private String bestHand;
    private int win;
    private int stake;
    private int bet;

    @Override
    public void init() {
        for(int i = 0; i < 5; i++){
            cardImages.add("/images/CardBack.png");
        }
    }

    private void doDeal(){
        game.deal();
        dealEnabled = false;
        update();
    }

    private void update() {
        List<Card> cards = game.getHand().getCards();
        List<String> dealtCardImages = new ArrayList<String>();
        for(int i = 0; i < 5; ++i) {
            String card = "images/"+cards.get(i).getRank()+""+cards.get(i).getSuit()+".png";
            dealtCardImages.add(card);
        }
        cardImages = dealtCardImages;
        stake = game.getStake();
        bet = game.getBet();
        win = game.getWin();
//        Stake.InnerText = Game.Stake.ToString();
//        Bet.InnerText = Game.Bet.ToString();
//        Win.InnerText = Game.Win.ToString();
//        foreach(var checkbox in checkboxes) {
//            checkbox.Checked = false;
//            checkbox.Disabled = dealEnabled;
//        }
//        Draw.Visible = !dealEnabled;
//        Deal.Visible = dealEnabled;
//        currentRanking.InnerText = String.Format("tr.{0} {{ background-color: red; }}", Game.Hand.BestRanking);
        bestHand = game.getHand().getbestRanking().toString();
    }

    private void doDraw(String[] checkedCards){
        List<String> exchange = new ArrayList<String>();
        exchange.add("1");
        exchange.add("2");
        exchange.add("3");
        exchange.add("4");
        exchange.add("5");
        if(checkedCards!=null){
            for (String checkedCard : checkedCards) {
                exchange.remove(checkedCard);
            }
        }
        game.draw(toIntArray(exchange));
        dealEnabled = true;
        update();
    }

    private int[] toIntArray(List<String> list){
        int[] ret = new int[list.size()];
        for(int i = 0;i < ret.length;i++)
            ret[i] = Integer.parseInt(list.get(i))-1;
        return ret;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        String cardNumber = request.getParameter("card");
        String card="";
        if(!cardNumber.isEmpty()){
            int cardInteger = Integer.parseInt(cardNumber);
            card = cardImages.get(cardInteger-1);
        }
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        RequestDispatcher rd = request.getRequestDispatcher(card);
        rd.forward(request, response);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException,
            java.io.IOException {

        HttpSession session = req.getSession();
        if(game==null){
            game = (Game)session.getAttribute("game");
        }
        Boolean sessionDrawVisible = (Boolean)session.getAttribute("dealEnabled");
        String[] checkedCards = req.getParameterValues("checkedCards");

        if(dealEnabled){
            doDeal();
        }else{
            doDraw(checkedCards);
        }
        if(sessionDrawVisible != null){
            session.putValue("dealEnabled",dealEnabled);
        }else{
            session.setAttribute("dealEnabled", dealEnabled);
        }
        session.setAttribute("game",game);
        session.setAttribute("bestHand",bestHand);
        session.setAttribute("stake",stake);
        session.setAttribute("win",win);
        session.setAttribute("bet",bet);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
