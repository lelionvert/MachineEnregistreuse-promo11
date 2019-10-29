using System;
using System.Collections;
using NFluent;
using NUnit.Framework;

namespace CaisseEnregistreuse
{
    public class MyDataClass
    {
        public static IEnumerable PriceQueryTestCases
        {
            get
            {
                yield return new TestCaseData("APPLE", 1.20);
                yield return new TestCaseData("BANANA", 1.90);
            }
        }
        
        public static IEnumerable TotalResultFoundTestCases
        {
            get
            {
                yield return new TestCaseData("APPLE", 2, 1.20);
                yield return new TestCaseData("BANANA", 3, 1.90);
            }
        }
        
        public static IEnumerable TotalResultNotFoundTestCases
        {
            get
            {
                yield return new TestCaseData("PEACH", 2, 1.20);
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

        [TestCaseSource(typeof(MyDataClass), "PriceQueryTestCases")]
        [Test]
        public void find_the_price_given_an_item_code(string itemCode, double unitPrice)
        {
            Check.That(
                priceQuery.FindPrice(itemCode)
            ).IsEqualTo(Result.Found(Price.ValueOf(unitPrice)));
        }

        [TestCaseSource(typeof(MyDataClass), "PriceQueryNullTestCases")]
        [Test]
        public void find_the_price_given_an_null_item_code(string itemCode, double unitPrice)
        {
            Check.That(
                priceQuery.FindPrice(itemCode)
            ).IsEqualTo(Result.NotFound(itemCode));
        }
        
        [TestCaseSource(typeof(MyDataClass), "TotalResultFoundTestCases")]
        [Test]
        public void next_step_find_the_price_given_an_item_code(string itemCode, int quantity, double unitPrice)
        {
            Result total = CaisseEnregistreuse.Total(priceQuery.FindPrice(itemCode), Quantity.ValueOf(quantity));
            
            Check.That(total).IsEqualTo(Result.Found(Price.ValueOf(quantity * unitPrice)));
        }
        
        [TestCaseSource(typeof(MyDataClass), "TotalResultNotFoundTestCases")]
        [Test]
        public void next_step_find_the_price_given_an_null_item_code(string itemCode, int quantity, double unitPrice)
        {
            Result total = CaisseEnregistreuse.Total(priceQuery.FindPrice(itemCode), Quantity.ValueOf(quantity));
            
            Check.That(total).IsEqualTo(Result.NotFound(itemCode));
        }
    }
}