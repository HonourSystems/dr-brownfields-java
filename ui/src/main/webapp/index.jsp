<%--
  Created by IntelliJ IDEA.
  User: michael
  Date: 28/07/13
  Time: 4:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<%@ page isELIgnored ="false" %>
<html>
<head>
    <title>Video Poker</title>
    <style type="text/css">
        img {
            width: 160px;
            height: 200px;
        }

        input {
            width: 25px;
            margin-left: 50px;
        }

        button {
            width: 100px;
        }

        div.card {
            margin: 5px;
            float: left;
        }

        div.deal {
            width: 100px;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
            padding: 0px;
        }

        td.Ranking {
            width: 200px;
            float: left;
            padding: 5px;
        }

        td.Payout {
            width: 50px;
            text-align: right;
            padding: 5px;
        }

        td.Caption {
            width: 80px;
        }

        td.Value {
            width: 25px;
            text-align: right;
        }

        div {
            margin-left: auto;
            margin-right: auto;
        }

        table.Betting {
            float: right;
            margin-right: 60px;
            border-collapse: separate;
            border-spacing: 10px;
        }
    </style>
    <style  id="currentRanking">
        tr.Nothing {
            background-color: red;
        }
    </style>
    <script type="text/javascript">
        function Select(hold) {
            if (hold.disabled == false) {
                hold.checked = !hold.checked;
            }
        }
    </script>
    <style>
        tr.${sessionScope.bestHand} {
            background-color: red;
        }
    </style>
</head>
<body>

<form name="form1" method="post" action="/gameServer" style="width: 900px;">
    <h1>Dr. Brownfield's Poker Emporium</h1>
    <table class="Betting">
        <tr>
            <td class="Caption">Stake</td>
            <td class="Value" id="Stake">${sessionScope.stake}</td>
        </tr>
        <tr>
            <td>Bet</td>
            <td class="Value"  id="Bet">${sessionScope.bet}</td>
        </tr>
        <tr>
            <td>Win</td>
            <td class="Value"  id="Win">${sessionScope.win}</td>
        </tr>
    </table>
    <table class="Rankings">

        <tr class="RoyalFlush">
            <td class="Ranking">Royal Flush</td>
            <td class="Payout">2000</td>
        </tr>
        <tr class="StraightFlush">
            <td class="Ranking">Straight Flush</td>
            <td class="Payout">250</td>
        </tr>
        <tr class="FourOfAKind">
            <td class="Ranking">Four of a Kind</td>
            <td class="Payout">125</td>
        </tr>
        <tr class="FullHouse">
            <td class="Ranking">Full House</td>
            <td class="Payout">40</td>
        </tr>
        <tr class="Flush">
            <td class="Ranking">Flush</td>
            <td class="Payout">25</td>
        </tr>
        <tr class="Straight">
            <td class="Ranking">Straight</td>
            <td class="Payout">20</td>
        </tr>
        <tr class="ThreeOfAKind">
            <td class="Ranking">Three of a Kind</td>
            <td class="Payout">15</td>
        </tr>
        <tr class="TwoPair">
            <td class="Ranking">Two Pair</td>
            <td class="Payout">10</td>
        </tr>
        <tr class="Pair">
            <td class="Ranking">Pair</td>
            <td class="Payout">5</td>
        </tr>
    </table>
    <div class="card">
        <div>
            <img id="Card1" src="/gameServer?card=1" onclick="Select(Hold1);" />
        </div>
        <div>
            <input type="checkbox" name="checkedCards" value="1" id="Hold1" />Hold
        </div>
    </div>
    <div class="card">
        <div>
            <img id="Card2" src="/gameServer?card=2" onclick="Select(Hold2);" />
        </div>
        <div>
            <input type="checkbox" name="checkedCards" value="2" id="Hold2" />Hold
        </div>
    </div>
    <div class="card">
        <div>
            <img id="Card3" src="/gameServer?card=3" onclick="Select(Hold3);" />
        </div>
        <div>
            <input type="checkbox" name="checkedCards" value="3" id="Hold3" />Hold
        </div>
    </div>
    <div class="card">
        <div>
            <img id="Card4" src="/gameServer?card=4" onclick="Select(Hold4);" />
        </div>
        <div>
            <input type="checkbox" name="checkedCards" value="4" id="Hold4" />Hold
        </div>
    </div>
    <div class="card">
        <div>
            <img  id="Card5" src="/gameServer?card=5" onclick="Select(Hold5);" />
        </div>
        <div>
            <input  type="checkbox" name="checkedCards" value="5" id="Hold5" />Hold
        </div>
    </div>

    <div class="deal">
        <c:choose>
            <c:when test="${sessionScope.dealEnabled}"><input type="submit" name="draw" disabled="disabled">Draw</input></c:when>
            <c:otherwise><input type="submit" name="draw">Draw</input></c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${!sessionScope.dealEnabled}"><input type="submit" name="deal" disabled="disabled">Deal</input></c:when>
            <c:otherwise><input type="submit" name="deal">Deal</input></c:otherwise>
        </c:choose>
    </div>

</form>
</body>
</html>