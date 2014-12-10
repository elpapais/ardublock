package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
//import com.ardublock.translator.block.TranslatorBlock;
//import com.ardublock.translator.block.exception.BlockException;

public class BlinkBlock extends TranslatorBlock 
{
	public static final String ARDUBLOCK_BLINK_DEFINE = "void __ardublockBlink(int pinNumber)\n{\npinMode(pinNumber, OUTPUT);\ndigitalWrite(pinNumber, HIGH);\ndelay(1000);\ndigitalWrite(pinNumber, LOW);\ndelay(1000);\n}\n";
	
	public BlinkBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock BlinkPin = this.getRequiredTranslatorBlockAtSocket(0);
		
		String pin = BlinkPin.toCode();
		
		/*if ((Integer.parseInt(pin) > 19 ) || (Integer.parseInt(pin) < 0)) 
		{
			throw new BlockException(this.blockId, "That pin number is out of range!");
		}*/
		String setupCode = "\tpinMode( " + pin + " , OUTPUT);"; 
		translator.addSetupCommand(setupCode);

		String ret = "\tdigitalWrite(" + pin + " , HIGH);\n";
		ret += "\tdelay(1000);\n";
		ret += "\tdigitalWrite(" + pin + " , LOW);\n";
		ret += "\tdelay(1000);\n";
		
		return ret;
	}

}