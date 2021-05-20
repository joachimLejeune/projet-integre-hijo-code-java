package view.classe.threads;

import view.classe.form.CounterPanelForm;

public class TotalBillCountingThread extends Thread {
    private CounterPanelForm counterPanelForm;

    public TotalBillCountingThread(CounterPanelForm counterPanelForm){
        this.counterPanelForm = counterPanelForm;
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(10);
                counterPanelForm.repaint();
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
}
