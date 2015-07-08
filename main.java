import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;
class RunnableDemo implements Runnable {
   private Thread t;
   private String threadName;
   public static long queueWaitTime = 0;
   public static long queueWaitNum = 0;
   private static Queue<String> qe=new LinkedList<String>();
	public static int randInt(int min, int max) {
		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
	public static synchronized void addQueueWaitTime(long time) {
	    queueWaitTime+=time;
	}
	public static synchronized void addQueueWaitNum() {
	    queueWaitNum++;
	}
   public static synchronized void incrementCount(String id) {
        qe.add(id);
    }
    public static synchronized void decrement() {
        qe.poll();
    }
	public static synchronized int queueSize() {
		return qe.size();
	}
   RunnableDemo( String name){
       threadName = name;
       // System.out.println("Creating " +  threadName );
   }
   public void run() {
      // System.out.println("Running " +  threadName );
      try {
      	int numQueueSize = queueSize();
      	if(numQueueSize>0){
      			System.out.println("Process "+threadName+" queued");
      			addQueueWaitNum();
      		}
		incrementCount(threadName);
		// System.out.println("thread is " + threadName + " queue is " + qe.element());
        // if(qe.size()>0){
		        long startTime = System.currentTimeMillis();

      		// }
		while(qe.element() != threadName){
			// System.out.println("thread is " + threadName + "queue is " + qe.element());
			Thread.sleep(50);
		}
		if(numQueueSize>0){
			long stopTime = System.currentTimeMillis();
     	 	long elapsedTime = (stopTime - startTime)/1000;
		    System.out.println("queue wait time for process "+threadName+" is "+elapsedTime);
		    addQueueWaitTime(elapsedTime);
		}
				// System.out.println("poll from queue "+threadName);

		// wait to do process
		int enterTime = randInt(1,5);
		System.out.println("begin process " + threadName + " with wait time " + enterTime);
		try {
			Thread.sleep(enterTime*1000); //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		} //wait miliseconds
		decrement();
        System.out.println("Finish " + threadName);
		// System.out.println("thread is " + threadName + "queue is " + qe.element());

Thread.sleep(50); //should be in loop
         // for(int i = 4; i > 0; i--) {
         //    System.out.println("Thread: " + threadName + ", " + i);
         //    // Let the thread sleep for a while.
         //    
         // }

     } catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
     }
     // System.out.println("Thread " +  threadName + " exiting.");
   }
   
   public void start ()
   {
      // System.out.println("Starting " +  threadName );
      if (t == null)
      {
         t = new Thread (this, threadName);
         t.start ();
      }
   }

}

public class main {
   // private static Queue<String> qe=new LinkedList<String>();

   // public static synchronized void incrementCount(String id) {
   //      qe.add(id);
   //  }
   public static void main(String args[]) {
       // System.out.println("Queue poll :"+qe.poll());
   //     String  threadName = Integer.toString(1);
   //     System.out.println("go to queue");
			// incrementCount(threadName);
			// System.out.println(threadName);
			// System.out.println("threadName");
	  //  	System.exit(0);
	  	RunnableDemo R1;
		for (int i = 1; i < 7 ; i++) {
			if(i != 1){
				//wait to enter
				int enterTime = randInt(1,6);
				System.out.println("Wait " + enterTime + " seconds And enter entity " + i);
				try {
	    			Thread.sleep(enterTime*1000);                 //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				} //wait miliseconds
			}
			System.out.println("Enter entity " + i);
			R1 = new RunnableDemo( Integer.toString(i));
      		R1.start();
      		if(i == 6){
				try {
					Thread.sleep(2000*5);                 //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				} //wait miliseconds
				System.out.println("Average queue Wait Time is "+R1.queueWaitTime/6.0);

				System.out.println("Percent process waited is "+((100.0*R1.queueWaitNum)/6));
				System.out.println("# process waited is "+R1.queueWaitNum);

      		}
	   }
      // RunnableDemo R1 = new RunnableDemo( "Thread-1");
      // R1.start();
      
      // RunnableDemo R2 = new RunnableDemo( "Thread-2");
      // R2.start();
	// while(R1.queueSize() != 0){
	// 	try {
 //    			Thread.sleep(6000);                 //1000 milliseconds is one second.
	// 		} catch(InterruptedException ex) {
	// 		    Thread.currentThread().interrupt();
	// 		} //wait miliseconds
	// }
	// if(R0.queueSize() == 0){
		System.out.println("This is end ");
	// }
   }   


   	public static int randInt(int min, int max) {

    // NOTE: Usually this should be a field rather than a method
    // variable so that it is not re-seeded every call.
    Random rand = new Random();

    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
}
}