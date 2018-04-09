
public class Emoji {

	
	public String getEmoji(String emoji) {

		if (emoji.equals("jcar")) {
			return "\u2615 \uD83D\uDCF2 \uD83D\uDE97";
		}
		
		if (emoji.equals("bat")){
			return "\uD83D\uDD0B \u26A1";
		}

		if (emoji.equals("bal")){
			return "\uD83C\uDF10 \uD83D\uDCB0";
		}
		if (emoji.equals("gas")){
			return "\u26FD \uD83D\uDCC9";
		}
		if (emoji.equals("gps")){
			return "\uD83D\uDD0E \uD83C\uDF0E";
		}
		if (emoji.equals("gsm")){
			return "\uD83D\uDCE1 \uD83D\uDCF6";
		}

		if (emoji.equals("net")){
			return "\u23F3 \uD83C\uDF10";
		}

		if (emoji.equals("tsp")){
			return "\u231A \uD83D\uDD50";
		}
		
		return null;
	}
	
}
