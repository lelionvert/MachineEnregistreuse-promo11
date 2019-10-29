using System;
using System.Collections;
using NFluent;
using NUnit.Framework;

namespace CaisseEnregistreuse
{
    public class MyDataClass
    {
        public static IEnumerable TestCases
        {
            get
            {
                yield return new TestCaseData(Price.ValueOf(1.20), Quantity.ValueOf(1), Price.ValueOf(1.20));
                yield return new TestCaseData(Price.ValueOf(2.50), Quantity.ValueOf(3), Price.ValueOf(7.50));
                yield return new TestCaseData(Price.ValueOf(12), Quantity.ValueOf(3), Price.ValueOf(36));
            }
        }

        public static IEnumerable PriceQueryTestCases
        {
            get
            {
                yield return new TestCaseData("APPLE", 1.20);
                yield return new TestCaseData("BANANA", 1.90);
            }
        }

        public static IEnumerable PriceQueryNullTestCases
        {
            get { yield return new TestCaseData("PEACH", 2.40); }
        }
    }

    [TestFixture]
    public class Tests
    {
        private IPriceQuery priceQuery;
        
        [SetUp]
        public void Setup()
        {
            priceQuery = new InMemoryCatalog(
                ItemReference.Builder()
                    .WithItemCode("APPLE")
                    .WithUnitPrice(1.20)
                    .Build(),
                ItemReference.Builder()
                    .WithItemCode("BANANA")
                    .WithUnitPrice(1.90)
                    .Build()
            );
        }

        [TestCaseSource(typeof(MyDataClass), "TestCases")]
        public void TotalTest(Price price, Quantity quantity, Price expected)
        {
            var result = CaisseEnregistreuse.Total(price, quantity);
            Check.That(result).IsEqualTo(expected);
        }

        [TestCaseSource(typeof(MyDataClass), "PriceQueryTestCases")]
        [Test]
        public void find_the_price_given_an_item_code(string itemCode, double unitPrice)
        {
            Check.That(
                priceQuery.FindPrice(itemCode)
            ).IsInstanceOf<Result.FoundResult>();
        }

        [TestCaseSource(typeof(MyDataClass), "PriceQueryNullTestCases")]
        [Test]
        public void find_the_price_given_an_null_item_code(string itemCode, double unitPrice)
        {
            Check.That(
                priceQuery.FindPrice(itemCode)
            ).IsInstanceOf<Result.NotFoundResult>();
        }
    }
}