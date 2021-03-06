package victor.training.java8.functionalpatterns;

import static java.util.Optional.empty;

import java.util.Optional;

/* "I call it my billion-dollar mistake. 
 * It was the invention of the null reference in 1965..."
 *  -- Sir Charles Antony Richard  */

// Get a discount line to print in UI

class DiscountService {
	public String getDiscountLine(Customer customer) {
		return customer.getMemberCard()
				.flatMap(card -> getApplicableDiscountPercentage(card))
				.map(discount -> "Discount: " +discount)
				.orElse("");
		
		
	}
		
	private Optional<Integer> getApplicableDiscountPercentage(MemberCard card) {
		if (card.getFidelityPoints() >= 100) {
			return Optional.of(5);
		}
		if (card.getFidelityPoints() >= 50) {
			return Optional.of(3);
		}
		return empty(); // pisica moarta
	}
		
	// test: 60, 10, no MemberCard
	public static void main(String[] args) {
		DiscountService service = new DiscountService();
		System.out.println(service.getDiscountLine(new Customer(new MemberCard(60))));
		System.out.println(service.getDiscountLine(new Customer(new MemberCard(10))));
		System.out.println(service.getDiscountLine(new Customer()));
	}
}








// VVVVVVVVV ==== supporting (dummy) code ==== VVVVVVVVV
class Customer {
	private MemberCard memberCard;
	public Customer() {
	}
	public Customer(MemberCard profile) {
		this.memberCard = profile;
	}
	public Optional<MemberCard> getMemberCard() {
		return Optional.ofNullable(memberCard);
	}
}

class MemberCard {
	private final int fidelityPoints;

	public MemberCard(int fidelityPoints) {
		this.fidelityPoints = fidelityPoints;
	}

	public int getFidelityPoints() {
		return fidelityPoints;
	}
	
}
