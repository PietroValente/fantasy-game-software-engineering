public class Cella
{
    Matrice.Tipologia tipoC; //tipologia cella
    int nOrchi; //numero orchi
    int nElfi; //numero elfi
    int nNani; //numero nani
    int nPezzi; //numero pezzi della casella

    public Cella(Matrice.Tipologia t)
    {
        nPezzi=0;
        nOrchi=0;
        nElfi=0;
        nNani=0;
        tipoC=t;
    }

    public int valDef(int tempo) //tempo = 0 significa giorno, tempo = 1 significa notte
    {
        int defElfi = 0;
        int defNani = 0;
        int defOrchi = 0;

        //difesa elfi
        if(tipoC == Matrice.Tipologia.BOSCO)
        {
            defElfi=4*nElfi;
        }
        else
        {
            defElfi=2*nElfi;
        }

        //difesa nani
            defNani=5*nNani;

        //difesa orchi
        if(tempo==0)
        {
            defOrchi=2*nOrchi;
        }
        else
        {
            defOrchi=6*nOrchi;
        }

        return defElfi+defNani+defOrchi;
    }

    public int valAtt(int tempo) //tempo = 0 significa giorno, tempo = 1 significa notte
    {
        int attElfi = 0;
        int attNani = 0;
        int attOrchi = 0;

        //attacco elfi
            attElfi=5*nElfi;

        //attacco nani
        if(tipoC == Matrice.Tipologia.MONTAGNA)
        {
            attNani=4*nNani;
        }
        else
        {
            attNani=2*nNani;
        }

        //attacco orchi
        if(tempo==0)
        {
            attOrchi=2*nOrchi;
        }
        else
        {
            attOrchi=6*nOrchi;
        }

        return attElfi+attNani+attOrchi;
    }
}