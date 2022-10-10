package Logic;

public class SunCoinManager {
	private int suns = 0;

	public SunCoinManager(int suns) {
		this.suns = suns;
	}

	public boolean pay(int quant) {
		if (suns < quant) {
			return false;
		}
		suns = suns - quant;
		return true;
	}
	
	public void deposit(int quant) {
		suns = suns + quant;
	}

	public int getSuncoins() {
		return suns;
	}
	
	public void setSunCoins(int n)
	{
		suns = n;
	}
}
