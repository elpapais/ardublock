package com.ardublock.translator.block.sparkfun;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Blink extends TranslatorBlock 
{
	public Blink(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
			
			
		TranslatorBlock BlinkPin = this.getRequiredTranslatorBlockAtSocket(0);
		
		String pin = BlinkPin.toCode();
		
		if ((Integer.parseInt(pin) > 19 ) || (Integer.parseInt(pin) < 0)) 
		{
			throw new BlockException(this.blockId, "That pin number is out of range!");
		}
		String setupCode = "\tpinMode( " + pin + " , OUTPUT);"; 
		translator.addSetupCommand(setupCode);

		String ret = "\tdigitalWrite(" + pin + " , HIGH);\n";
		ret += "\tdelay(1000);\n";
		ret += "\tdigitalWrite(" + pin + " , LOW);\n";
		ret += "\tdelay(1000);\n";
		
		return ret;
	}

}
