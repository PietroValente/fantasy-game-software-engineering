public class Main 
{
    public static void main(String[] args)
    {
        InputFile infoFile = new InputFile(); //tutte le informazioni legate al file di input
        Matrice grigliaGioco = new Matrice(infoFile.nRighe, infoFile.nColonne); //creo la griglia di gioco
        infoFile.riempiMatrice(grigliaGioco); //inserimento pezzi
        Richieste menu = new Richieste(grigliaGioco);//passo alle richieste sulle celle
    }
}