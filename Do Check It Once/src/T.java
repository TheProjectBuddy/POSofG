import java.io.*;


public class T
{
	public static void main(String s[]) {
		
		try 
		{
			
			File file = new File("src/menu/item.txt");
			FileReader fileReader = new FileReader(file);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			System.out.println(bufferedReader.readLine());
			System.out.println(bufferedReader.readLine());
			bufferedReader.close();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
