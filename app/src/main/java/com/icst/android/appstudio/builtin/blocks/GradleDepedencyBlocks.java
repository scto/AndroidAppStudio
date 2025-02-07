/*
 * This file is part of Android AppStudio [https://github.com/Innovative-CST/AndroidAppStudio].
 *
 * License Agreement
 * This software is licensed under the terms and conditions outlined below. By accessing, copying, modifying, or using this software in any way, you agree to abide by these terms.
 *
 * 1. **  Copy and Modification Restrictions  **
 *    - You are not permitted to copy or modify the source code of this software without the permission of the owner, which may be granted publicly on GitHub Discussions or on Discord.
 *    - If permission is granted by the owner, you may copy the software under the terms specified in this license agreement.
 *    - You are not allowed to permit others to copy the source code that you were allowed to copy by the owner.
 *    - Modified or copied code must not be further copied.
 * 2. **  Contributor Attribution  **
 *    - You must attribute the contributors by creating a visible list within the application, showing who originally wrote the source code.
 *    - If you copy or modify this software under owner permission, you must provide links to the profiles of all contributors who contributed to this software.
 * 3. **  Modification Documentation  **
 *    - All modifications made to the software must be documented and listed.
 *    - the owner may incorporate the modifications made by you to enhance this software.
 * 4. **  Consistent Licensing  **
 *    - All copied or modified files must contain the same license text at the top of the files.
 * 5. **  Permission Reversal  **
 *    - If you are granted permission by the owner to copy this software, it can be revoked by the owner at any time. You will be notified at least one week in advance of any such reversal.
 *    - In case of Permission Reversal, if you fail to acknowledge the notification sent by us, it will not be our responsibility.
 * 6. **  License Updates  **
 *    - The license may be updated at any time. Users are required to accept and comply with any changes to the license.
 *    - In such circumstances, you will be given 7 days to ensure that your software complies with the updated license.
 *    - We will not notify you about license changes; you need to monitor the GitHub repository yourself (You can enable notifications or watch the repository to stay informed about such changes).
 * By using this software, you acknowledge and agree to the terms and conditions outlined in this license agreement. If you do not agree with these terms, you are not permitted to use, copy, modify, or distribute this software.
 *
 * Copyright © 2024 Dev Kumar
 */

package com.icst.android.appstudio.builtin.blocks;

import java.util.ArrayList;

import com.icst.android.appstudio.block.model.BlockFieldLayerModel;
import com.icst.android.appstudio.block.model.BlockFieldModel;
import com.icst.android.appstudio.block.model.BlockHolderLayer;
import com.icst.android.appstudio.block.model.BlockHolderModel;
import com.icst.android.appstudio.block.model.BlockLayerModel;
import com.icst.android.appstudio.block.model.BlockModel;
import com.icst.android.appstudio.block.model.BlockValueFieldModel;
import com.icst.android.appstudio.block.utils.RawCodeReplacer;
import com.icst.android.appstudio.builtin.blockfield.PackageNameBlockField;

public class GradleDepedencyBlocks {
	public static ArrayList<BlockHolderModel> getGradleDepedencyBlocks() {
		ArrayList<BlockHolderModel> holders = new ArrayList<BlockHolderModel>();
		BlockHolderModel holder = new BlockHolderModel();
		holder.setColor("#E27625");
		holder.setName("dependency");

		ArrayList<Object> dependencyBlocksList = new ArrayList<Object>();
		dependencyBlocksList.add(getImplementationBlock(true, true, null));

		holder.setList(dependencyBlocksList);

		holders.add(holder);
		return holders;
	}

	public static ArrayList<BlockHolderModel> getGradleAndroidBlocks() {
		ArrayList<BlockHolderModel> holders = new ArrayList<BlockHolderModel>();
		BlockHolderModel holder = new BlockHolderModel();
		holder.setColor("#1B0B54");
		holder.setName("Android");

		ArrayList<Object> androidBlocksList = new ArrayList<Object>();

		androidBlocksList.add(getNameSpaceBlock(true, null));

		holder.setList(androidBlocksList);

		holders.add(holder);
		return holders;
	}

	public static BlockModel getNameSpaceBlock(boolean allowDrag, String nameSpace) {
		BlockModel block = new BlockModel();
		block.setBlockType(BlockModel.Type.defaultBlock);
		block.setColor("#0061FE");
		block.setReplacerKey("namespaceBlock");
		block.setDragAllowed(allowDrag);

		StringBuilder rawCode = new StringBuilder();
		rawCode.append("namespace \"");
		rawCode.append(RawCodeReplacer.getReplacer(block.getReplacerKey(), "namespace"));
		rawCode.append("\"");

		block.setRawCode(rawCode.toString());

		ArrayList<BlockLayerModel> blockLayers = new ArrayList<BlockLayerModel>();

		BlockFieldLayerModel layer1 = new BlockFieldLayerModel();

		ArrayList<BlockFieldModel> layer1Fields = new ArrayList<BlockFieldModel>();

		BlockFieldModel nameSpaceText = new BlockFieldModel();
		nameSpaceText.setValue("namespace");

		PackageNameBlockField inputNameSpaceField = new PackageNameBlockField();
		inputNameSpaceField.setReplacer("namespace");
		if (nameSpace != null) {
			inputNameSpaceField.setValue(nameSpace);
		}

		layer1Fields.add(nameSpaceText);
		layer1Fields.add(inputNameSpaceField);

		layer1.setBlockFields(layer1Fields);
		blockLayers.add(layer1);
		block.setBlockLayerModel(blockLayers);
		return block;
	}

	public static BlockModel getImplementationBlock(
			boolean allowDrag, boolean allowLibraryChange, String libraryValue) {
		BlockModel implementationBlock = new BlockModel();
		implementationBlock.setBlockType(BlockModel.Type.defaultBlock);
		implementationBlock.setColor("#E27625");
		implementationBlock.setRawCode("implementation");
		implementationBlock.setReplacerKey("implementationBlock");
		implementationBlock.setDragAllowed(allowDrag);

		StringBuilder rawCode = new StringBuilder();
		rawCode.append("implementation \"");
		rawCode.append(RawCodeReplacer.getReplacer(implementationBlock.getReplacerKey(), "library"));
		rawCode.append("\"");

		implementationBlock.setRawCode(rawCode.toString());

		ArrayList<BlockLayerModel> implementationBlockLayers = new ArrayList<BlockLayerModel>();

		BlockFieldLayerModel implementationBlockLayer1 = new BlockFieldLayerModel();

		ArrayList<BlockFieldModel> implementationBlockLayer1Fields = new ArrayList<BlockFieldModel>();

		BlockFieldModel implementationText = new BlockFieldModel();
		implementationText.setValue("implementation");

		BlockValueFieldModel inputDependencyField = new BlockValueFieldModel();
		inputDependencyField.setFieldType(BlockValueFieldModel.FieldType.FIELD_INPUT_ONLY);
		inputDependencyField.setReplacer("library");
		inputDependencyField.setEnableEdit(allowLibraryChange);
		if (libraryValue != null) {
			inputDependencyField.setValue(libraryValue);
		}

		implementationBlockLayer1Fields.add(implementationText);
		implementationBlockLayer1Fields.add(inputDependencyField);

		implementationBlockLayer1.setBlockFields(implementationBlockLayer1Fields);

		implementationBlockLayers.add(implementationBlockLayer1);

		implementationBlock.setBlockLayerModel(implementationBlockLayers);

		return implementationBlock;
	}

	public static BlockModel getDefaultConfigBlock(boolean allowDrag) {
		BlockModel defaultConfigBlock = new BlockModel();
		defaultConfigBlock.setBlockType(BlockModel.Type.defaultBlock);
		defaultConfigBlock.setColor("#4759B8");
		defaultConfigBlock.setReplacerKey("defaultConfigBlock");
		defaultConfigBlock.setDragAllowed(allowDrag);

		StringBuilder rawCode = new StringBuilder();
		rawCode.append("defaultConfig {\n\t");
		rawCode.append(RawCodeReplacer.getReplacer(defaultConfigBlock.getReplacerKey(), "config"));
		rawCode.append("\n}");

		defaultConfigBlock.setRawCode(rawCode.toString());

		ArrayList<BlockLayerModel> defaultConfigBlockLayers = new ArrayList<BlockLayerModel>();

		// Layer 1st
		BlockFieldLayerModel defaultConfigBlockLayer1 = new BlockFieldLayerModel();

		ArrayList<BlockFieldModel> defaultConfigBlockLayer1Fields = new ArrayList<BlockFieldModel>();

		BlockFieldModel defaultConfigText = new BlockFieldModel();
		defaultConfigText.setValue("defaultConfig");

		defaultConfigBlockLayer1Fields.add(defaultConfigText);

		defaultConfigBlockLayer1.setBlockFields(defaultConfigBlockLayer1Fields);

		// Layer 2nd
		BlockHolderLayer defaultConfigBlockLayer2 = new BlockHolderLayer();
		defaultConfigBlockLayer2.setReplacer("config");

		defaultConfigBlockLayers.add(defaultConfigBlockLayer1);
		defaultConfigBlockLayers.add(defaultConfigBlockLayer2);

		defaultConfigBlock.setBlockLayerModel(defaultConfigBlockLayers);

		return defaultConfigBlock;
	}
}
