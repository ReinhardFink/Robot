# Robot
Playground for Threads with "Robots" in a JTextArea.

Robot are programmed straight forward. 

This means, they are not realized as state-machines, where a act-loop checks and changes states, like:

    act() {
        if (free())
            move();
        else
            turn();
    }

So programmes look more like:

    while(free())
       move();

in Main.java you can choose between 3 options, by changing the comment:

    // the easy way, to try to simulate a normal console WITHOU Threads
    aFrame.setContentPane(new ConsolePanel_NO_Threads());
		
    // the easy way, to simulate a normal console WITH asynchronous Threads
    //aFrame.setContentPane(new ConsolePanel_WITH_ASYN_Threads());
		
    // the easy way, to simulate a normal console WITH synchronous Threads
    //aFrame.setContentPane(new ConsolePanel_WITH_SYNCED_Threads());

    
## NO Threads:
Everything is done in the AWT-Dispatcher-Thread.

This version is not working very good. You won't see any animation, because repaint() calls will be added to the end of the event-queue.

So while the AWT-Dispatcher-Thread is working on our 

while(free()) move(), 

it is not able do process any GUI updates.

:-(

  
## ASYCHRONOUS Threads:
  Every Robot get its own Thread. Functions, which changes direction like turn() or location like move() will call paint().
  
  paint() updates the JTextArea synchronized.
  
  Implementing the STOP-button adds a lot of throws InterruptedException to move() and others, to return from an while(free()) move().
  
  :-(
  
  So an interrupt in code like this
  
    public void free() throws InterruptedException {
        ...
    }
    
    public void move() throws InterruptedException {
        ...
    }

  
    public void work() throws InterruptedException {
        while(free()) 
            move();
    }

will be caught in run().
    
    public void run() {
        try {
            work();
        } catch (InterruptedException e) {
            CONSTANTS.printThreadInfo("interrupt()");
        }
    }
    
    
## SYCHRONOUS Threads:

  Every Robot get its own Thread. Functions, which changes direction like turn() or location like move() will call paint().
  
  paint() updates does not update JTextArea itself! 
  
  It writes to the char[] and then just requires a lock in paint()
  
    synchronized (this) {
        this.wait();
    }
  
  A javax.swing.Timer, implemented in the associated ConsolePanel (ConsolePanel_WITH_SYNCED_Threads) updates the GUI in the AWT-Dispatcher-Thread,
  and opens the lock on all waiting Threads with 
  
    synchronized (room) {
        room.notifyAll();
    }
    
### ATTENTION:
  You have to take care, that NO Thread, which is NOT a Robot calls paint()!
  
  Because if e.g. the Main/AWT-Dispacher thread calls paint(), these Threads will stuck in this.wait()
  
  :-( :-(
  
