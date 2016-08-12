package com.pgmacdesign.pgmacutilities;

import android.graphics.Color;

/**
 * Created by pmacdowell on 8/12/2016.
 */
public class ColorUtilities {

    /**
     * Parse a color (Handles the parsing errors)
     * @param color String color to parse
     * @return If not parseable or an error occurrs, it will send back -100.
     */
    public static int parseMyColor(String color){
        if(StringUtilities.isNullOrEmpty(color)){
            return -100;
        }
        try {
            int x = Color.parseColor(color);
            return x;
        } catch (Exception e){
            return -100;
        }
    }
    /*
     <color name="Semi_Transparent1">#20111111</color>
    <color name="Semi_Transparent2">#30111111</color>
    <color name="Semi_Transparent3">#40111111</color>
    <color name="Semi_Transparent4">#50111111</color>
    <color name="Semi_Transparent5">#69111111</color>
    <color name="Semi_Transparent6">#79111111</color>
    <color name="Semi_Transparent7">#89111111</color>
    <color name="Semi_Transparent8">#99111111</color>
    <color name="Semi_Transparent9">#A9111111</color>



    <color name="Transparent">#00000000</color>
    <color name="White">#FFFFFF</color>
    <color name="Ivory">#FFFFF0</color>
    <color name="LightYellow">#FFFFE0</color>
    <color name="Yellow">#FFFF00</color>
    <color name="Snow">#FFFAFA</color>
    <color name="FloralWhite">#FFFAF0</color>
    <color name="LemonChiffon">#FFFACD</color>
    <color name="Cornsilk">#FFF8DC</color>
    <color name="Seashell">#FFF5EE</color>
    <color name="LavenderBlush">#FFF0F5</color>
    <color name="PapayaWhip">#FFEFD5</color>
    <color name="BlanchedAlmond">#FFEBCD</color>
    <color name="MistyRose">#FFE4E1</color>
    <color name="Bisque">#FFE4C4</color>
    <color name="Moccasin">#FFE4B5</color>
    <color name="NavajoWhite">#FFDEAD</color>
    <color name="PeachPuff">#FFDAB9</color>
    <color name="Gold">#FFD700</color>
    <color name="Pink">#FFC0CB</color>
    <color name="LightPink">#FFB6C1</color>
    <color name="Orange">#FFA500</color>
    <color name="LightSalmon">#FFA07A</color>
    <color name="DarkOrange">#FF8C00</color>
    <color name="Coral">#FF7F50</color>
    <color name="HotPink">#FF69B4</color>
    <color name="Tomato">#FF6347</color>
    <color name="OrangeRed">#FF4500</color>
    <color name="DeepPink">#FF1493</color>
    <color name="Fuchsia">#FF00FF</color>
    <color name="Magenta">#FF00FF</color>
    <color name="Red">#FF0000</color>
    <color name="OldLace">#FDF5E6</color>
    <color name="LightGoldenrodYellow">#FAFAD2</color>
    <color name="Linen">#FAF0E6</color>
    <color name="AntiqueWhite">#FAEBD7</color>
    <color name="Salmon">#FA8072</color>
    <color name="GhostWhite">#F8F8FF</color>
    <color name="MintCream">#F5FFFA</color>
    <color name="WhiteSmoke">#F5F5F5</color>
    <color name="Beige">#F5F5DC</color>
    <color name="Wheat">#F5DEB3</color>
    <color name="SandyBrown">#F4A460</color>
    <color name="Azure">#F0FFFF</color>
    <color name="Honeydew">#F0FFF0</color>
    <color name="AliceBlue">#F0F8FF</color>
    <color name="Khaki">#F0E68C</color>
    <color name="LightCoral">#F08080</color>
    <color name="PaleGoldenrod">#EEE8AA</color>
    <color name="Violet">#EE82EE</color>
    <color name="DarkSalmon">#E9967A</color>
    <color name="Lavender">#E6E6FA</color>
    <color name="LightCyan">#E0FFFF</color>
    <color name="BurlyWood">#DEB887</color>
    <color name="Plum">#DDA0DD</color>
    <color name="Gainsboro">#DCDCDC</color>
    <color name="Crimson">#DC143C</color>
    <color name="PaleVioletRed">#DB7093</color>
    <color name="Goldenrod">#DAA520</color>
    <color name="Orchid">#DA70D6</color>
    <color name="Thistle">#D8BFD8</color>
    <color name="LightGrey">#D3D3D3</color>
    <color name="LightGreyFaded">#77D3D3D3</color>
    <color name="Tan">#D2B48C</color>
    <color name="Chocolate">#D2691E</color>
    <color name="Peru">#CD853F</color>
    <color name="IndianRed">#CD5C5C</color>
    <color name="MediumVioletRed">#C71585</color>
    <color name="Silver">#C0C0C0</color>
    <color name="DarkKhaki">#BDB76B</color>
    <color name="RosyBrown">#BC8F8F</color>
    <color name="MediumOrchid">#BA55D3</color>
    <color name="DarkGoldenrod">#B8860B</color>
    <color name="FireBrick">#B22222</color>
    <color name="PowderBlue">#B0E0E6</color>
    <color name="LightSteelBlue">#B0C4DE</color>
    <color name="PaleTurquoise">#AFEEEE</color>
    <color name="GreenYellow">#ADFF2F</color>
    <color name="LightBlue">#ADD8E6</color>
    <color name="DarkGray">#A9A9A9</color>
    <color name="Brown">#A52A2A</color>
    <color name="Sienna">#A0522D</color>
    <color name="YellowGreen">#9ACD32</color>
    <color name="DarkOrchid">#9932CC</color>
    <color name="PaleGreen">#98FB98</color>
    <color name="DarkViolet">#9400D3</color>
    <color name="MediumPurple">#9370DB</color>
    <color name="LightGreen">#90EE90</color>
    <color name="DarkSeaGreen">#8FBC8F</color>
    <color name="SaddleBrown">#8B4513</color>
    <color name="DarkMagenta">#8B008B</color>
    <color name="DarkRed">#8B0000</color>
    <color name="BlueViolet">#8A2BE2</color>
    <color name="LightSkyBlue">#87CEFA</color>
    <color name="SkyBlue">#87CEEB</color>
    <color name="Gray">#808080</color>
    <color name="Olive">#808000</color>
    <color name="Purple">#800080</color>
    <color name="Maroon">#800000</color>
    <color name="Aquamarine">#7FFFD4</color>
    <color name="Chartreuse">#7FFF00</color>
    <color name="LawnGreen">#7CFC00</color>
    <color name="MediumSlateBlue">#7B68EE</color>
    <color name="LightSlateGray">#778899</color>
    <color name="SlateGray">#708090</color>
    <color name="OliveDrab">#6B8E23</color>
    <color name="SlateBlue">#6A5ACD</color>
    <color name="DimGray">#696969</color>
    <color name="MediumAquamarine">#66CDAA</color>
    <color name="CornflowerBlue">#6495ED</color>
    <color name="CadetBlue">#5F9EA0</color>
    <color name="DarkOliveGreen">#556B2F</color>
    <color name="Indigo">#4B0082</color>
    <color name="MediumTurquoise">#48D1CC</color>
    <color name="DarkSlateBlue">#483D8B</color>
    <color name="SteelBlue">#4682B4</color>
    <color name="RoyalBlue">#4169E1</color>
    <color name="Turquoise">#40E0D0</color>
    <color name="MediumSeaGreen">#3CB371</color>
    <color name="LimeGreen">#32CD32</color>
    <color name="DarkSlateGray">#2F4F4F</color>
    <color name="SeaGreen">#2E8B57</color>
    <color name="ForestGreen">#228B22</color>
    <color name="LightSeaGreen">#20B2AA</color>
    <color name="DodgerBlue">#1E90FF</color>
    <color name="MidnightBlue">#191970</color>
    <color name="Aqua">#00FFFF</color>
    <color name="Cyan">#00FFFF</color>
    <color name="SpringGreen">#00FF7F</color>
    <color name="Lime">#00FF00</color>
    <color name="MediumSpringGreen">#00FA9A</color>
    <color name="DarkTurquoise">#00CED1</color>
    <color name="DeepSkyBlue">#00BFFF</color>
    <color name="DarkCyan">#008B8B</color>
    <color name="Teal">#008080</color>
    <color name="Green">#008000</color>
    <color name="DarkGreen">#006400</color>
    <color name="Blue">#0000FF</color>
    <color name="MediumBlue">#0000CD</color>
    <color name="DarkBlue">#00008B</color>
    <color name="Navy">#000080</color>
    <color name="Black">#000000</color>

    <color name="white">#FFFFFF</color>
    <color name="black">#000000</color>
    <color name="yellow">#FFFF00</color>
    <color name="fuchsia">#FF00FF</color>
    <color name="red">#FF0000</color>
    <color name="silver">#C0C0C0</color>
    <color name="gray">#808080</color>
    <color name="olive">#808000</color>
    <color name="purple">#800080</color>
    <color name="maroon">#800000</color>
    <color name="aqua">#00FFFF</color>
    <color name="lime">#00FF00</color>
    <color name="teal">#008080</color>
    <color name="green">#008000</color>
    <color name="blue">#0000FF</color>
    <color name="navy">#000080</color>
     */
}
