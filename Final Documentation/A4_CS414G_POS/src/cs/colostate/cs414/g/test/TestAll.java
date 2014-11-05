package cs.colostate.cs414.g.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cs.colostate.cs414.g.domain.Payment;


@RunWith(Suite.class)
@SuiteClasses({ OrderTest.class, MenuItemModificationTest.class, MenuItemTest.class, MenuTest.class, PaymentByCardTest.class, PaymentByCashTest.class, PaymentTest.class })
public class TestAll {

}