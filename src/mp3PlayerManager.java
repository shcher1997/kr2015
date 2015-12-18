

        import java.io.BufferedReader;
        import java.io.ByteArrayInputStream;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.MalformedURLException;
        import java.net.URL;

        import javazoom.jl.decoder.JavaLayerException;
        import javazoom.jl.player.AudioDevice;
        import javazoom.jl.player.FactoryRegistry;
        import javazoom.jl.player.Player;
        public class mp3PlayerManager implements Runnable{
    private byte [] buffer;
    private ByteArrayInputStream inStream;
    private FileInputStream inFileStream;
    private Player player;
    /**
     * if local mp3 , mode = 0;
     * if remote mp3 , mode = 1;
     * if radio, mode = 2;
     *
     */
    private int mode;
    private boolean paused = false, stopped = true;

    protected AudioDevice getAudioDevice()
            throws JavaLayerException
    {
        return FactoryRegistry.systemRegistry().createAudioDevice();
    }

    public mp3PlayerManager (){
    }

    public void setLocalMp3 (String filename) throws IOException, JavaLayerException{
        mode = 0;
        inFileStream = new FileInputStream("D:\\ФКН КС-12\\oop\\kr2015\\sounds\\astronaut.mp3");
        player = new Player(inFileStream, getAudioDevice());
    }

    public void setRemoteMp3 (byte [] buf) throws JavaLayerException{
        mode = 1;
        this.buffer = buf;
        this.inStream = new ByteArrayInputStream(buffer);
        player = new Player(inStream, getAudioDevice());
    }

    public int getCurrentPosition (){
        return this.player.getPosition();
    }

    public void play (){
        if (player != null) {
            paused = false;
            stopped = false;
            new Thread(this).start();
        }
    }

    public void pause (){
       // this.player.pause();
        this.paused = true;
    }

    public void resume (){
       // this.player.resume();
        this.paused = false;
    }

    public void stop (){
      //  this.player.pause();
        this.player.close();
        this.stopped = true;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("starting...");
        try {
            System.out.println("playing...");
            this.player.play();
        } catch (JavaLayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void pauseToggle (){
        this.paused = !this.paused;
        if (this.paused){
            System.out.println("player paused");
           // this.player.pause();
        }
        else {
            System.out.println("player playing");
          //  this.player.resume();
        }
    }

    public static void main (String args[]){
        mp3PlayerManager mp3man = new mp3PlayerManager();
        try {
           mp3man.setLocalMp3(("D:\\ФКН КС-12\\oop\\kr2015\\sounds\\astronaut.mp3"));
            //  mp3man.setLocalMp3(new java.net.URL("file:///~/any.mp3"));
            mp3man.play();
            BufferedReader consoleReader = new BufferedReader   (new InputStreamReader(System.in));
            while (1 == 1)
            {
                try
                {
                    consoleReader.readLine();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

                System.out.println("toggling pause");
                System.out.println(mp3man.getCurrentPosition());

                mp3man.pauseToggle();
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JavaLayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}