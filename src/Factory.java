import java.util.concurrent.Semaphore;

public class Factory
{
	private static final int nA = 3;
	private static final int nB = 2;
	private static final int totA = 1000;
	private static final int totB = 1000;
	private int currentA;
	private int currentB;

	private Semaphore mutex;
	private Semaphore needProd;

	public Factory()
	{
		mutex    = new Semaphore(1);
		needProd = new Semaphore(0);
				
		currentA = totA;
		currentB = totB;
	}

	public void prepara_prodotto(int id_operaio)
	{
		try
		{
			mutex.acquire();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		// se uno dei due ingredienti non ha una quantità sufficiente per preparare
		// il prodotto quindi richiedo al fattorino di riempire le scorte di ingredienti
		if ((currentA < nA) || (currentB < nB))
		{ 
			System.out.println("Prodotto mancante. Richiesta fornitura al fattorino da operaio id=" + id_operaio);

			// Sblocco il thread del fattorino che così riempirà le quantità di ingredienti
			needProd.release();

			try
			{
				// faccio in modo che il thread del fattorino si possa svegliare
				Thread.sleep(1);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// Creo il prodotto
		currentA -= nA;
		currentB -= nB;

		System.out.println("L'operaio ID=" + id_operaio + " ha prelevato le quantità per preparare un prodotto: A=" + currentA + " B=" + currentB );

		mutex.release();
	}

	public void scorta_ingredienti()
	{
		try
		{
			// Questo thread è sempre in pending su questo semaforo aspettando che qualcuno 
			// (un operaio) lo sblocchi per permettergli di rifornire gli ingredienti 
			needProd.acquire();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		// Ricarica gli ingredienti
		currentA = totA;
		currentB = totB;
		
		System.out.println("Il fattorino ha rifornito le quantità di prodotti.");
	}
}
