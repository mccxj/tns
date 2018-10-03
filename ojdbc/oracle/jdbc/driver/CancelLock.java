/*    */ package oracle.jdbc.driver;
/*    */ class CancelLock
/*    */ {
/*    */   private static enum State
/*    */   {
/* 56 */     IDLE,  EXECUTING,  CANCELING;
/*    */     private State() {} }
/* 58 */   private State state = State.IDLE;
/*    */   
/*    */   synchronized void enterExecuting()
/*    */   {
/* 62 */     assert (this.state == State.IDLE);
/* 63 */     this.state = State.EXECUTING;
/*    */   }
/*    */   
/*    */   synchronized void exitExecuting()
/*    */   {
/* 68 */     while (this.state != State.EXECUTING)
/*    */     {
/* 70 */       assert (this.state == State.CANCELING);
/*    */       try {
/* 72 */         wait();
/*    */       } catch (InterruptedException localInterruptedException) {}
/*    */     }
/* 75 */     this.state = State.IDLE;
/*    */   }
/*    */   
/*    */   synchronized boolean enterCanceling()
/*    */   {
/* 80 */     if (this.state == State.EXECUTING)
/*    */     {
/* 82 */       this.state = State.CANCELING;
/* 83 */       return true;
/*    */     }
/*    */     
/* 86 */     return false;
/*    */   }
/*    */   
/*    */   synchronized void exitCanceling()
/*    */   {
/* 91 */     assert (this.state == State.CANCELING);
/* 92 */     this.state = State.EXECUTING;
/* 93 */     notify();
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/CancelLock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */