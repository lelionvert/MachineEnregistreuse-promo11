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
    }
    [TestFixture]
    public class Tests
    {
        [SetUp]
        public void Setup()
        {
        }

        [TestCaseSource(typeof(MyDataClass), "TestCases")]
        public void TotalTest(Price price, Quantity quantity, Price expected)
        {
            var result = CaisseEnregistreuse.Total(price, quantity);
            Check.That(result).IsEqualTo(expected);
        }
    }
}