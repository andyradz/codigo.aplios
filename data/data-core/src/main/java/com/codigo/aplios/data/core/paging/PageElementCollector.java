package com.codigo.aplios.data.core.paging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PageElementCollector implements Collector<PageElement, List<PageElement>, IPageable<PageElement>> {

	public static void main(String[] args) {
		Set<Claim> claims = new HashSet<>();
		claims.add(new Claim(Claim.PRODUCT_TYPE.MOTOR));
		claims.add(new Claim(Claim.PRODUCT_TYPE.MOTOR));
		claims.add(new Claim(Claim.PRODUCT_TYPE.MOTOR));

		claims.add(new Claim(Claim.PRODUCT_TYPE.HOUSEHOLD));
		claims.add(new Claim(Claim.PRODUCT_TYPE.HOUSEHOLD));

		ClaimProductTypeCollector<Claim> claimProductTypeCollector = new ClaimProductTypeCollector();
		claimProductTypeCollector.getRequiredTypes()
			.add(Claim.PRODUCT_TYPE.MOTOR);
		claimProductTypeCollector.getRequiredTypes()
			.add(Claim.PRODUCT_TYPE.HOUSEHOLD);
		Map<Claim, Claim> oneClaimPerProductType = claims.stream()
			.collect(claimProductTypeCollector);
	}

	@Override
	public Supplier<List<PageElement>> supplier() {
		return ArrayList::new;
	}

	@Override
	public BiConsumer<List<PageElement>, PageElement> accumulator() {
		return List::add;
	}

	@Override
	public BinaryOperator<List<PageElement>> combiner() {
		return (acc, ps) -> {
			acc.addAll(ps);
			return acc;
		};
	}

	@Override
	public Function<List<PageElement>, IPageable<PageElement>> finisher() {
		return null;
		// return (points) -> {
		//
		// return PageResult.ofList1(points);
		//
		// };
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.UNORDERED));
	}
}

class Claim {

	public enum PRODUCT_TYPE {
		MOTOR, HOUSEHOLD, TRAVEL
	}

	private PRODUCT_TYPE productType;

	public Claim(PRODUCT_TYPE productType) {
		this.productType = productType;
	}

	public PRODUCT_TYPE getProductType() {
		return productType;
	}

	public void setProductType(PRODUCT_TYPE productType) {
		this.productType = productType;
	}

}

class ClaimProductTypeCollector<T extends Claim> implements Collector<T, Map, Map> {

	private Set<Claim.PRODUCT_TYPE> requiredTypes = new HashSet<>();

	public Set<Claim.PRODUCT_TYPE> getRequiredTypes() {
		return requiredTypes;
	}

	@Override
	public Supplier<Map> supplier() {
		return () -> new HashMap<>();
	}

	@Override
	public BiConsumer<Map, T> accumulator() {
		return (map, claim) -> {
			if (map.get(claim.getProductType()) == null) {
				map.put(claim.getProductType(), claim);
			}
		};
	}

	@Override
	public Function<Map, Map> finisher() {
		return Function.identity();
	}

	@Override
	public BinaryOperator<Map> combiner() {
		return null;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.singleton(Characteristics.IDENTITY_FINISH);
	}
}