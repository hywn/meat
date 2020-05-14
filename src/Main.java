public class Main
{
	/**
	 * runs program. takes no command-line args
	 *
	 * @param args command-line args
	 */
	public static void main(String[] args)
	{
		Game game = new Game();

		new Thread(game).start();
	}
}
