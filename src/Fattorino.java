public class Fattorino extends Thread
{
	private Factory factory;

	public Fattorino(Factory factory)
	{
		this.factory = factory;
	}

	public void run()
	{
		while (true)
		{
			factory.scorta_ingredienti();
			
			try
			{
				sleep(500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}

		}
	}
}
