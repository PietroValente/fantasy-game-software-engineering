import java.util.Scanner;

public class Richieste
{
    Matrice grigliaGioco;

    public Richieste(Matrice m)
    {
        grigliaGioco = m;

        while(true)
        {
            //menu
            System.out.println("\nSelezionare il numero della richiesta selezionata oppure digitare exit per chiudere il programma\n");
            System.out.println("1. Il numero di pezzi presenti sulla mappa per ciascuna tipologia");
            System.out.println("2. La casella con il maggior valore di difesa di giorno");
            System.out.println("3. La casella con il maggior valore di difesa di notte");
            System.out.println("4. La casella con il maggior valore di attacco di giorno");
            System.out.println("5. La casella con il maggior valore di attacco di notte");
            System.out.println("6. La casella con il maggior numero di pezzi dello stesso tipo\n");

            Scanner sk = new Scanner(System.in);
            String comando = sk.nextLine();
            
            if(comando.equalsIgnoreCase("exit"))
            {
                System.exit(0);
            }
            else if(comando.equalsIgnoreCase("1"))
            {
                numPezzi();
            }
            else if(comando.equalsIgnoreCase("2"))
            {
                maxDef(0);
                System.out.println("con maggior valore di difesa di giorno");
            }
            else if(comando.equalsIgnoreCase("3"))
            {
                maxDef(1);
                System.out.println("con maggior valore di difesa di notte");
            }
            else if(comando.equalsIgnoreCase("4"))
            {
                maxAtt(0);
                System.out.println("con maggior valore di attacco di giorno");
            }
            else if(comando.equalsIgnoreCase("5"))
            {
                maxAtt(1);
                System.out.println("con maggior valore di attacco di notte");
            }
            else if(comando.equalsIgnoreCase("6"))
            {
                maxPezzi();
            }
            else
            {
                System.out.println("\nComando non riconosciuto, riprovare");
            }
        }
    }

    private void numPezzi()
    {
        int numElfi=0;
        int numNani=0;
        int numOrchi=0;

        for(int i=0; i<grigliaGioco.colonne; i++)
        {
            for(int j=0; j<grigliaGioco.righe; j++)
            {
                numElfi = numElfi + grigliaGioco.matrice[j][i].nElfi;
                numNani =  numNani + grigliaGioco.matrice[j][i].nNani;
                numOrchi = numOrchi + grigliaGioco.matrice[j][i].nOrchi;
            }
        }

        System.out.println("\nNumero totale elfi: "+numElfi);
        System.out.println("Numero totale nani: "+numNani);
        System.out.println("Numero totale orchi: "+numOrchi);
    }

    private void maxDef(int tempo) //tempo = 0 significa giorno, tempo = 1 significa notte
    {
        int valore = 0;
        int riga=-1;
        int colonna=-1;
        int nCelle=0;
        String celle="";


        for(int i=0; i<grigliaGioco.colonne; i++)
        {
            for(int j=0; j<grigliaGioco.righe; j++)
            {
                if(grigliaGioco.matrice[j][i].valDef(tempo) > valore)
                {
                    valore=grigliaGioco.matrice[j][i].valDef(tempo);
                    riga = j;
                    colonna = i;
                    nCelle=1;
                    celle="("+j+","+i+") ";
                }
                else if(grigliaGioco.matrice[j][i].valDef(tempo) == valore)
                {
                    celle= celle + "("+j+","+i+") ";
                    nCelle++;
                }
            }
        }

        if(riga==-1)
        {
            System.out.print("\nNon ci sono pezzi, quindi non esiste la casella ");
            return;
        }
        else if(nCelle ==1)
        {
            System.out.print("\nLa casella ("+riga+","+colonna+") con valore difensivo di "+valore+" e' la casella ");
        }
        else
        {
            System.out.print("\nLe caselle "+celle+"con valore difensivo di "+valore+" sono le caselle ");
        }
    }

    private void maxAtt(int tempo) //tempo = 0 significa giorno, tempo = 1 significa notte
    {
        int valore = 0;
        int riga=-1;
        int colonna=-1;
        int nCelle=0;
        String celle="";

        for(int i=0; i<grigliaGioco.colonne; i++)
        {
            for(int j=0; j<grigliaGioco.righe; j++)
            {
                if(grigliaGioco.matrice[j][i].valAtt(tempo) > valore)
                {
                    valore=grigliaGioco.matrice[j][i].valAtt(tempo);
                    riga = j;
                    colonna = i;
                    nCelle=1;
                    celle="("+j+","+i+") ";
                }
                else if(grigliaGioco.matrice[j][i].valAtt(tempo) == valore)
                {
                    celle= celle + "("+j+","+i+") ";
                    nCelle++;
                }
            }
        }

        if(riga==-1)
        {
            System.out.print("\nNon ci sono pezzi, quindi non esiste la casella ");
            return;
        }
        else if(nCelle ==1)
        {
            System.out.print("\nLa casella ("+riga+","+colonna+") con valore offensivo di "+valore+" e' la casella ");
        }
        else 
        {
            System.out.print("\nLe caselle "+celle+"con valore offensivo di "+valore+" sono le caselle ");
        }
    }

    private void maxPezzi()
    {
        int numero = 0;
        String tipo=null;
        int riga=-1;
        int colonna=-1;
        int nCelle=0;
        String celle="";

        for(int i=0; i<grigliaGioco.colonne; i++)
        {
            for(int j=0; j<grigliaGioco.righe; j++)
            {
                if(grigliaGioco.matrice[j][i].nElfi>numero || grigliaGioco.matrice[j][i].nNani>numero || grigliaGioco.matrice[j][i].nOrchi>numero)
                {
                    if(grigliaGioco.matrice[j][i].nElfi>grigliaGioco.matrice[j][i].nNani)
                    {
                        if(grigliaGioco.matrice[j][i].nElfi>grigliaGioco.matrice[j][i].nOrchi)
                        {
                            numero = grigliaGioco.matrice[j][i].nElfi;
                        }
                        else 
                        {
                            numero = grigliaGioco.matrice[j][i].nOrchi;
                        }
                    }
                    else 
                    {
                        if(grigliaGioco.matrice[j][i].nNani>grigliaGioco.matrice[j][i].nOrchi)
                        {
                            numero = grigliaGioco.matrice[j][i].nNani;
                        }
                        else 
                        {
                            numero = grigliaGioco.matrice[j][i].nOrchi;
                        }
                    }
                    riga = j;
                    colonna = i;
                    nCelle=1;
                    celle="("+j+","+i+") ";
                }
                else if(grigliaGioco.matrice[j][i].nElfi==numero || grigliaGioco.matrice[j][i].nNani==numero || grigliaGioco.matrice[j][i].nOrchi==numero)
                {
                    celle= celle + "("+j+","+i+") ";
                    nCelle++;
                }
            }
        }

        if(riga==-1)
        {
            System.out.println("\nNon ci sono pezzi");
            return;
        }
        else if(nCelle ==1)
        {
            System.out.println("\nLa casella ("+riga+","+colonna+") con "+numero+" pezzi uguali e' la casella con piu' pezzi dello stesso tipo");
        }
        else 
        {
            System.out.println("\nLe caselle "+celle+"con "+numero+" pezzi uguali sono le caselle con piu' pezzi dello stesso tipo");
        }
    }
}