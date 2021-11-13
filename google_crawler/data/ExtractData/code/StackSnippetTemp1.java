public class StackSnippetTemp {
public void test(){
<Window x:Class="WpfApplication8.Window1"
    xmlns="http://schemas.microsoft.com/winfx/2006/xaml/presentation"
    xmlns:x="http://schemas.microsoft.com/winfx/2006/xaml"       
    xmlns:chartingToolkit="clr-namespace:System.Windows.Controls.DataVisualization.Charting;assembly=System.Windows.Controls.DataVisualization.Toolkit"
    xmlns:visualizationToolkit="clr-namespace:System.Windows.Controls.DataVisualization;assembly=System.Windows.Controls.DataVisualization.Toolkit"
    xmlns:Primitives="clr-namespace:System.Windows.Controls.DataVisualization.Charting.Primitives;assembly=System.Windows.Controls.DataVisualization.Toolkit" 
    Title="Window1" Height="500" Width="500">
<Window.Resources>
    <Style x:Key="LineSeriesStyle1" TargetType="{x:Type chartingToolkit:LineSeries}">
        <Setter Property="IsTabStop" Value="False"/>
        <Setter Property="Template">
            <Setter.Value>
                <ControlTemplate TargetType="{x:Type chartingToolkit:LineSeries}">
                    <Canvas x:Name="PlotArea">

                        <!--You can use linergradient or visulbrush to give color to polyline -->

                        <!--or you can use additional polylines to achieve  this affect-->
                        <Polyline Fill="Transparent"  StrokeThickness="200" Points="{TemplateBinding Points}">
                            <Polyline.Stroke>
                                <SolidColorBrush Color="#9FBD5F"></SolidColorBrush>
                            </Polyline.Stroke>
                            <Polyline.BitmapEffect>
                                <BlurBitmapEffect Radius="100"  KernelType="Box" />
                            </Polyline.BitmapEffect>
                        </Polyline>                        
                        <Polyline x:Name="polynine" Points="{TemplateBinding Points}" StrokeThickness="5"   Stroke="#2DB14D"/>
                    </Canvas>
                </ControlTemplate>
            </Setter.Value>
        </Setter>
    </Style>
</Window.Resources>

<chartingToolkit:Chart x:Name="mcChart" >
    <chartingToolkit:LineSeries DependentValuePath="Value"  IsSelectionEnabled="True" IndependentValuePath="Key" ItemsSource="{Binding}" Style="{StaticResource LineSeriesStyle1}"/>
</chartingToolkit:Chart>

}
}