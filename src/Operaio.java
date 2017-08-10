
public class Operaio extends Thread
{
	private Factory factory;
	private int id;

	public Operaio(Factory factory, int id)
	{
		this.factory = factory;
		this.id = id;
	}

	public void run()
	{
		while (true)
		{
			factory.prepara_prodotto(this.id);
			try
			{
				sleep(500);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
