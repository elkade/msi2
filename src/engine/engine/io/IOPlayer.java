// Copyright 2016 theaigames.com (developers@theaigames.com)

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

package engine.engine.io;

import bot.BotParser;

/**
 * IOPlayer class
 * 
 * Handles the communication between the bot process and the engine
 * 
 * @author Jackie Xu <jackie@starapple.nl>, Jim van Eeden <jim@starapple.nl>
 */
public class IOPlayer {

    private BotParser botParser;
    private StringBuilder dump;
    private int errorCounter;
    private boolean finished;
    private String idString;

    private final int MAX_ERRORS = 2;

    public String response;

    public IOPlayer(BotParser botParser, String idString) {
        this.botParser = botParser;
        this.idString = idString;
        this.dump = new StringBuilder();
        this.errorCounter = 0;
        this.finished = false;
    }

    /**
     * Write a string to the bot
     * @param line : input string
     */
    public void writeToBot(String line) {
        if (!this.finished) {
            String responseFromBot = botParser.processCommand(line);
            if (responseFromBot != null) {
                this.response = responseFromBot;
            }
            addToDump(line);
        }
    }

    public String getResponse(long timeOut) {
        if (this.errorCounter > this.MAX_ERRORS) {
            addToDump(String.format("Maximum number (%d) of time-outs reached: skipping all moves.", this.MAX_ERRORS));
            return "";
        }
        return this.response;
    }

    public void finish() {
        if (this.finished) {
            return;
        }
        this.finished = true;
    }

    /**
     * @return : String representation of the bot ID as used in the database
     */
    public String getIdString() {
        return this.idString;
    }

    /**
     * Adds a string to the bot dump
     * @param dumpy : string to add to the dump
     */
    public void addToDump(String dumpy) {
        dump.append(dumpy + "\n");
    }

    /**
     * Add a warning to the bot's dump that the engine outputs
     * @param warning : the warning message
     */
    public void outputEngineWarning(String warning) {
        dump.append(String.format("Engine warning: \"%s\"\n", warning));
    }

    /**
     * @return : the dump of all the IO
     */
    public String getDump() {
        return dump.toString();
    }

}
