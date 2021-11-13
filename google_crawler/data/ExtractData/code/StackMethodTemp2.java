public class StackMethodTemp {
public Window1()
    {
        InitializeComponent();
        LoadColumnChartData();
    }
    private void LoadColumnChartData()
    {
        ((LineSeries)mcChart.Series[0]).ItemsSource =
        new KeyValuePair<string, int>[]{
        new KeyValuePair<string,int>("Project Manager", 13),
        new KeyValuePair<string,int>("CEO", 23),};

    }
}