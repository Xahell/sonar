package client.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.Main;

/**
 * Transforms a text by replacing a set of tokens by
 * predefined values.
 * 
 * Example:
 * 
 * I am {name} => I am Robert
 * 
 * A token should be a name between brackets: {token}.
 * 
 * @author Administrateur
 */
public class Transformer {

	private static Logger logger = LoggerFactory.getLogger(Transformer.class);
	
	private Map<String, String> tokens = new HashMap<String, String>();
	
	/**
	 * Adds a new token which will used when transforming texts.
	 * 
	 * @param token The identifier of the token, without the brackets : "name".
	 * @param value The value which will replace the token in the text.
	 */
	public void addToken(String token, String value)
	{
		tokens.put(token, value);
	}
	
	/**
	 * Transforms a text by replacing the token with their corresponding values.
	 * 
	 * Each token should be between brackets, for example :
	 * 
	 * Hello {name}, how are you doing?
	 * 
	 * If a token is not known, then it is left in place as-is.
	 * 
	 * @param text The take which will be transformed.
	 * @return The transformed text.
	 */
	public String transform(String text)
	{
		// About 120% of the initial text size
		int capacity = (int) (text.length() * 1.2f);
		
		StringBuilder stringBuilder = new StringBuilder(capacity);
		
		Pattern pattern = Pattern.compile("\\{(.*?)\\}");
		Matcher matcher = pattern.matcher(text);
		
		// Pour chaque occurence de token dans le texte
		while (matcher.find())
		{
			// récupérer le nom du token
			String token = matcher.group(1);
			
			// Récupérer la valeur associée au token
			String value = tokens.get(token);
			
			// Remplacer le texte
			if (value != null)
				matcher.appendReplacement(stringBuilder, value);
			else
				matcher.appendReplacement(stringBuilder, "{" + token + "}");
		}
		
		// Ajouter la fin du texte restant
		matcher.appendTail(stringBuilder);
		
		return stringBuilder.toString();
	}
	
	public static void main(String[] args)
	{
		Transformer georgeTransformer = new Transformer();

		georgeTransformer.addToken("name", "George");
		georgeTransformer.addToken("address", "78, rue Will Smith");
		georgeTransformer.addToken("phone", "03 54 87 69 88");
		georgeTransformer.addToken("company", "Amazon");
		
		logger.info(georgeTransformer.transform("Bonjour {name}, comment vas-tu {name} ?"));
	}
}
