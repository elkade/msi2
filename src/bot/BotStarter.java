// // Copyright 2015 theaigames.com (developers@theaigames.com)

//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at

//        http://www.apache.org/licenses/LICENSE-2.0

//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//	
//    For the full copyright and license information, please view the LICENSE
//    file that was distributed with this source code.

package bot;


/**
 * BotStarter class
 * 
 * Magic happens here. You should edit this file, or more specifically the
 * makeTurn() method to make your bot do more than random moves.
 * 
 * @author Jim van Eeden <jim@starapple.nl>, Joost de Meij <joost@starapple.nl>
 */
public class BotStarter {

    private IBot bot;

    /**
     * Makes a turn. Edit this method to make your bot smarter.
     *
     * @return The column where the turn was made.
     */
    public int makeTurn(Field field) {
        return bot.getMove(field);
    }

    public static void main(String[] args) {
        String[] weightsStr = args[0].split(";", -1);

        double[] weights = new double[weightsStr.length];
        int i = 0;
        for (String str : weightsStr) {
            if (!str.isEmpty())
                weights[i++] = Double.parseDouble(str);
        }
        IBot bot = new Bot(weights);
        BotStarter botStarter = new BotStarter();
        botStarter.setBot(bot);

        BotParser parser = new BotParser(botStarter);
        parser.run();
    }

    public void setBot(IBot bot) {
        this.bot = bot;
    }

}
