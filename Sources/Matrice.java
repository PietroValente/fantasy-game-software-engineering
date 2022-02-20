public class Matrice 
{
    enum Tipologia{ PIANURA, BOSCO, MONTAGNA};

    int righe;
    int colonne;
    Cella[][] matrice;

    public Matrice(int r, int c)
    {
        righe = r;
        colonne = c;
        
        matrice = new Cella[righe][colonne];
        tipologiaCelle();
    }

    private void tipologiaCelle()
    {
        int colonna = 0;
        while(colonna<colonne)
        {
            if(colonna%3 == 0)
            {
                for(int i=0; i<righe;i++)
                {
                    matrice[i][colonna] = new Cella(Tipologia.PIANURA);
                }
            }
            else if(colonna%3 == 1)
            {
                for(int i=0; i<righe;i++)
                {
                    matrice[i][colonna] = new Cella(Tipologia.BOSCO);
                }
            }
            else
            {
                for(int i=0; i<righe;i++)
                {
                    matrice[i][colonna] = new Cella(Tipologia.MONTAGNA);
                }
            }
            colonna++;
        }
    }
}