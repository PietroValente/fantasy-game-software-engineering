import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class InputFile
{
  String nomefile;
  int rFile; //numero righe del file che parte da 1
  int nColonne; //numero colonne
  int nRighe; //numero righe
  Scanner fileSc; //scanner del file

  public InputFile()
  {
    Scanner utente = new Scanner(System.in);
    nColonne=0;
    nRighe=0;
    rFile=1;
    boolean flag = true;

    while(flag) //ciclo per assegnamento scanner
    {
      System.out.println("Inserire il nome del file oppure exit per terminare il programma");
      nomefile = utente.nextLine();
      if(nomefile.equalsIgnoreCase("exit"))
      {
        System.exit(0);
      }
      System.out.println();
      if(nomefile.length()<5 || !nomefile.substring(nomefile.length()-4).equals(".txt"))
      {
        System.out.println("Estensione del file diversa da .txt, quindi nome non valido\n");
        continue;
      }
      flag = newScanner();
    }
    
    verifica();
    newScanner();//ripristino scanner dopo verifica
  }

  private boolean newScanner()
  {
    try
      {
        fileSc= new Scanner(new FileReader(nomefile));
        return false;
      }
      catch(FileNotFoundException ecc)
      {
        System.out.println("File non trovato\n");
        return true;
      }
  }
  private void verifica()
  {
    if(!fileSc.hasNextLine())
    {
      System.out.println("Il file non ha righe");
      rFile=0;
      nColonne=1;
      nRighe=1;
      return;
    }

    while(fileSc.hasNextLine())
    {
      verificaInt(0);
      try
      {
        verificaInt(1);
        verificaString();
      }
      catch(NoSuchElementException ex)
      {
        System.out.println("Errore alla riga "+rFile+": devono essere scritte triplette di righe con le coordinate e la tipologia del pezzo");
        System.out.println("Modificare il file di input\n");
        System.exit(1);
      }
    }
    rFile--;
    nColonne++;
    nRighe++;
  }

  private void verificaInt(int mutex) //mutex=0 riga, mutex=1 colonna
  {
    int numero=-1;
    try
      {
        numero=Integer.parseInt(fileSc.nextLine());
        if(numero<0)
        {
          throw new NumberFormatException();
        }
      }
      catch(NumberFormatException e)
      {
        System.out.println("Errore alla riga "+rFile+": il numero deve essere un intero positivo e minore di 2147483647");
        System.out.println("Modificare il file di input\n");
        System.exit(1);
      }

      if(numero > nColonne && mutex==1)
      {
        nColonne=numero;
      }
      if(numero > nRighe && mutex==0)
      {
        nRighe=numero;
      }
      rFile++;
  }

  private void verificaString()
  {
    try
      {
        String stringa=fileSc.nextLine();
        if(!(stringa.equalsIgnoreCase("elfo") || stringa.equalsIgnoreCase("nano") || stringa.equalsIgnoreCase("orco")))
        {
          throw new NumberFormatException();
        }
      }
      catch(NumberFormatException e)
      {
        System.out.println("Errore alla riga "+rFile+": la stringa non corrisponde ad una delle tre tipologie elfo, nano o orco");
        System.out.println("Modificare il file di input\n");
        System.exit(1);
      }

      rFile++;
  }

  public void riempiMatrice(Matrice m) //funzione riempi matrice
  {
    if(!fileSc.hasNextLine())
    {
      System.out.println("Non ci sono pezzi da inserire");
      return;
    }

    while(fileSc.hasNextLine())
    {
      insPezzo(m,Integer.parseInt(fileSc.nextLine()),Integer.parseInt(fileSc.nextLine()),fileSc.nextLine());
    }
  }

  private void insPezzo(Matrice m, int riga, int colonna, String tipo)
  {
    if(m.matrice[riga][colonna].nPezzi == 5)
    {
      System.out.println("Nella casella ("+riga+","+colonna+") non posso aggiungere il pezzo "+tipo+" perche' ci sono gia' 5 pezzi, l'inserimento passera' al pezzo successivo");
      return;
    }

    if(tipo.equalsIgnoreCase("elfo"))
    {
      m.matrice[riga][colonna].nPezzi++;
      m.matrice[riga][colonna].nElfi++;
      System.out.println("Ho inserito un nuovo elfo nella casella ("+riga+","+colonna+")");
    }
    else if(tipo.equalsIgnoreCase("nano"))
    {
      m.matrice[riga][colonna].nPezzi++;
      m.matrice[riga][colonna].nNani++;
      System.out.println("Ho inserito un nuovo nano nella casella ("+riga+","+colonna+")");
    }
    else
    {
      m.matrice[riga][colonna].nPezzi++;
      m.matrice[riga][colonna].nOrchi++;
      System.out.println("Ho inserito un nuovo orco nella casella ("+riga+","+colonna+")");
    }
  }
}