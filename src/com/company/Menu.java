package com.company;

import java.util.*;

public class Menu implements IMenu{

    private List<Double> listOfAllowedCoins = new ArrayList<>(Arrays.asList(0.10,0.20,0.50,1.0,2.0,5.0));
    private ArrayList<Ticket> ticketsArray = new ArrayList<Ticket>();

    private MoneyStorage Biletomat = new MoneyStorage("PLN");
    private MoneyStorage thrownCoins = new MoneyStorage("PLN");





    @Override
    public void menu() {
        Biletomat.randomlistofcoins(listOfAllowedCoins);
        System.out.println(Biletomat.getSum().floatValue());
        Scanner in = new Scanner(System.in);
        while(true) {
            displayCostOfTickets();
            System.out.println("Wybierz Rodzaj Biletu:\n1. Bilet ulgowy\n2. Bilet normalny\n3. Platnosc\n4. Wyjdz");
            String choice = in.nextLine();
            try {
                int choice_int = Integer.parseInt(choice);
                if (choice_int == 1) {
                    menuUlgowe();
                } else if (choice_int == 2) {
                    menuNormalne();
                } else if (choice_int == 3) {
                    if(ticketsArray.isEmpty()){
                        System.out.println("Najpierw wybierz bilet");
                    } else
                        platnosc();
                } else if (choice_int == 4)
                    break;
                else System.out.println("Zły wybór");}

            catch(Exception e){

                System.out.println("Zły wybór");
            }
        }
    }

    @Override
    public void menuUlgowe() {
        Scanner in = new Scanner(System.in);
        System.out.println("Dostepne bilety ulgowe:\n1. Bilet 20 min \t 2.00zl\n2. Bilet 40 min \t 3.50zl \n3. Bilet 60 min \t 4.00zl \n4. Powrót\n");
        String choice = in.nextLine();
        try{int choice_int = Integer.parseInt(choice);
            if(choice_int==1){
                ticketsArray.add(new Ticket(2.0,"Ulgowy",20));
            }else if(choice_int==2){
                ticketsArray.add(new Ticket(3.50,"Ulgowy",40));
            }else if(choice_int==3){
                ticketsArray.add(new Ticket(4.0,"Ulgowy",60));
            }
            else if (choice_int==4){
                System.out.println("");
            }

            else System.out.println("Zły wybór");;}
        catch(Exception e){
            System.out.println("Zły wybór");;
        }
    }


    @Override
    public void menuNormalne() {
        Scanner in = new Scanner(System.in);
        System.out.println("Dostepne bilety normalne:\n1. Bilet 20 min \t 4.00zl\n2. Bilet 40 min \t 7.00zl \n3. Bilet 60 min \t 8.00zl \n4. Powrót\n");
        String choice = in.nextLine();
        try {
            int choice_int = Integer.parseInt(choice);
            if (choice_int == 1) {
                ticketsArray.add(new Ticket(4.0, "Normalny", 20));
            } else if (choice_int == 2) {
                ticketsArray.add(new Ticket(7.00, "Normalny", 40));
            } else if (choice_int == 3) {
                ticketsArray.add(new Ticket(8.0, "Normalny", 60));
            } else if (choice_int == 4) {
                // in.nextLine();
                //break;
            } else System.out.println("Zły wybór");;
        } catch (Exception e) {
            System.out.println("Zły wybór");
        }

    }
    @Override
    public void platnosc(){
        Scanner in = new Scanner(System.in);

        displayCostOfTickets();
        while(true){
            System.out.println("Wrzuć:\n1. 0.10zł\n2. 0.20zł\n3. 0.50zł\n4. 1.00zł\n5. 2.00zł\n6. 5.00zł\n7. Powrót\n8. Anuluj transakcję");
            String choice = in.nextLine();
            int choice_int = Integer.parseInt(choice);
            try {
                if (choice_int == 1) {
                    thrownCoins.addCoin(new Coin(0.10));
                }
                else if(choice_int == 2) {
                    thrownCoins.addCoin(new Coin(0.20));
                }
                else if(choice_int == 3) {
                    thrownCoins.addCoin(new Coin(0.50));
                }
                else if(choice_int == 4) {
                    thrownCoins.addCoin(new Coin(1.00));
                }
                else if(choice_int == 5) {
                    thrownCoins.addCoin(new Coin(2.00));
                }
                else if(choice_int == 6) {
                        thrownCoins.addCoin(new Coin(5.00));
                }
                else if(choice_int == 7) {
                    break;
                }
                else if(choice_int == 8) { //anulun trtaksakcje
                    if(thrownCoins.getSum().floatValue() == 0) break;

                    System.out.println("Wydano monety: ");
                    for (Coin t:
                            thrownCoins.returnList()) {
                        System.out.println(t.getValue().floatValue());
                    }
                    thrownCoins.deleteCoins();
                    break;
                }

            } catch (Exception e) {
                System.out.println("Zły wybór");
            }
            if(choice_int !=7) {
                displayCostOfTickets();
                System.out.println("Wrzucono monetę: " + thrownCoins.returnList().get(thrownCoins.returnList().size() - 1).getValue().floatValue());
                System.out.println("Suma wrzuconych monet: " + thrownCoins.getSum().floatValue());
            }

            if(thrownCoins.getSum().floatValue() >= totalTicketsCost()){
                if(thrownCoins.getSum().floatValue() == totalTicketsCost()){
                    System.out.println("Zakupione bilety:");
                    for (Ticket ticket: ticketsArray
                         ) {
                        System.out.println("Bilet " + ticket.getType() + " " + ticket.getDuration_min() + "-minutowy.");
                    }
                } else {
                    double tmp = thrownCoins.getSum().floatValue();
                    ArrayList<Double> tmplist = new ArrayList<Double>();
                    for (Coin coin:Biletomat.returnList()
                    ) {
                        tmplist.add(coin.getValue().doubleValue());
                    }
                    Collections.sort(tmplist, Collections.reverseOrder());
                    if(isChangePossible(tmplist)){
                        System.out.println("Zakupione bilety:");
                        for (Ticket ticket: ticketsArray
                        ) {
                            System.out.println("Bilet " + ticket.getType() + " " + ticket.getDuration_min() + "minutowy.");
                        }
                        System.out.println("Wydano " + (thrownCoins.getSum().floatValue() - totalTicketsCost()) + "zł reszty.");
                    }
                }
                ticketsArray.clear();
                for (Coin coin: thrownCoins.returnList()
                     ) {
                    Biletomat.addCoin(coin);
                }
                thrownCoins.deleteCoins();
                break;
            }
        }


    }
    public void displayCostOfTickets(){
        System.out.println("Ilosc biletow: " + ticketsArray.size() + " | Łączny koszt: " + totalTicketsCost() + "zł");
    }

    double totalTicketsCost(){
        double ticketsCostSum = 0;
        for (Ticket tikcet: ticketsArray) {
            ticketsCostSum+=tikcet.getCost();
        }
        return ticketsCostSum;
    }

    boolean isChangePossible(ArrayList<Double> tmplist){
        totalTicketsCost();
        double thrownSum =thrownCoins.getSum().floatValue();

        double change = thrownSum - totalTicketsCost();

        for(double i :tmplist){
            if(i == change){
                change -= i;
                return true;
            } else if (i < change){
                change -= i;
            }
        }
        return false;
    }

}


