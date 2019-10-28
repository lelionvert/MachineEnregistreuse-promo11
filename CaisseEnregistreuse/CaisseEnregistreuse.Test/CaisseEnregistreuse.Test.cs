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
                yield return new TestCaseData(new Price(1.20), 1, new Price(1.20));
                yield return new TestCaseData(new Price(2.50), 3, new Price(7.50));
                yield return new TestCaseData(new Price(12), 3, new Price(36));
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
        public void TotalTest(Price price, int quantity, Price expected)
        {
            var result = CaisseEnregistreuse.Total(price, quantity);
            Check.That(result).IsEqualTo(expected);
        }
    }
}