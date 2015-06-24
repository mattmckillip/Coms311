package homework7;

public class MattWeighing implements Weighing {

	@Override
	public double weight(Object e) {
		return (double) e;
	}

}
