class Track
{
	synchronized void tunnel(String rna,long sl)
	{
		System.out.println(" "+rna+" has entered into tunnel ");
		try {  Thread.sleep(sl);  }
		catch(Exception e)  { }
		System.out.println("  "+rna+" has crossed the tunnel");
	}
	synchronized void gn()
	{
		System.out.println(" Rabbit slept");
		try { wait(); }
		catch(Exception e) {}
		
		System.out.println(" Rabbit woke and ran");
	}
	
	synchronized void gm()
	{
		notify();
	}
}

class Racer implements Runnable
{
	Thread th;
	String rna;
	Track tr;
	long sl;
	int miles=1;
	
	//System.out.println(" Rac class");
	Racer(String na,long sl,Track tr)
	{
		th=new Thread(this);
		rna=na;
		this.sl=sl;
		this.tr=tr;
		th.start();
		//System.out.println("In racer const");
	}
	public void run()
	{
		//System.out.println("in run method");
		while(miles<=10)
		{
			//System.out.println("in while");
			if(miles==10)
			{
				System.out.println("  "+rna+" has won the race");
				System.exit(0);
			}
			else if(miles==3)
			{
				tr.tunnel(rna, sl);	
			}
			else if(miles==5 && rna.equals("Rabbit"))
			{
				tr.gn();
			}	
			else if(miles==7 && rna.equals("Tortoise"))
			{
				tr.gm();
			}
			System.out.println(rna+" has reached "+miles);
			try {  Thread.sleep(sl);  }
			catch(Exception e)  { }
			miles++;
		}
	}
}
public class Story 
{

	public static void main(String[] args) 
	{
		System.out.println("Main Class");
		Track tr=new Track();
		
		Racer r1=new Racer("Rabbit",3000,tr);
		Racer r2=new Racer("Tortoise",3500,tr);
	}
}
