import javax.swing.plaf.OptionPaneUI;

public class Main_TE_16_01_2017
{
	public static void main(String[] args)
	{
		Factory factory = new Factory();
		
		Thread operai[] = new Operaio[100]; 
		Thread fattorino = new Fattorino(factory);

		fattorino.start();
		int i=0;
		for (Thread operaio: operai)
		{
			operaio = new Operaio(factory, i++);
			operaio.start();
		}
	}

}
