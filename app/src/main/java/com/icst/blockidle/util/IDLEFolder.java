/*
 *  This file is part of Block IDLE.
 *
 *  Block IDLE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Block IDLE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *   along with Block IDLE.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.icst.blockidle.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.icst.blockidle.bean.IDLEFileBean;
import com.icst.blockidle.bean.IDLEFolderBean;
import com.icst.blockidle.exception.IDLEFileAlreadyExistsException;
import com.icst.blockidle.listener.SerializationListener;

public class IDLEFolder {

	// Contanst for contents of folder
	private static final String CONTENTS = "data";
	private static final String IDLEFOLDER = "IDLEFolder";
	private static final String IDLEFILE = "IDLEFile";

	private File file;

	public IDLEFolder(File file) {
		this.file = file;
	}

	public static IDLEFolder getProjectIDLEFolder(ProjectFile file) {
		return new IDLEFolder(file.getFile());
	}

	public List<IDLEFileBean> getFiles() {
		File contents = new File(file, CONTENTS);
		if (!contents.exists()) {
			return new ArrayList<IDLEFileBean>();
		}
		ArrayList<IDLEFileBean> filesList = new ArrayList<IDLEFileBean>();

		for (File file : contents.listFiles()) {

			// Direct files are not considered as IDLE files.
			if (file.isFile()) {
				continue;
			}

			if (new File(file, IDLEFOLDER).exists()) {
				File idleFolderFile = new File(file, IDLEFOLDER);
				IDLEFolderBean idleFolderBean = SerializationUtils.deserialize(idleFolderFile, IDLEFolderBean.class);

				// Deserialisation failed
				if (idleFolderBean == null) {
					continue;
				}

				filesList.add(idleFolderBean);

			} else if (new File(file, IDLEFILE).exists()) {
				File idleFile = new File(file, IDLEFILE);
				IDLEFileBean idleFileBean = SerializationUtils.deserialize(idleFile, IDLEFileBean.class);

				// Deserialisation failed
				if (idleFileBean == null) {
					continue;
				}

				filesList.add(idleFileBean);
			}
		}

		return filesList;
	}

	public IDLEFolder createFolder(String name) throws IDLEFileAlreadyExistsException {
		File contents = new File(file, CONTENTS);
		File folderRoot = new File(contents, name);

		if (!folderRoot.exists()) {
			folderRoot.mkdirs();
		}

		File idleFolderFile = new File(folderRoot, IDLEFOLDER);

		if (idleFolderFile.exists() || new File(folderRoot, IDLEFILE).exists()) {
			throw new IDLEFileAlreadyExistsException();
		}

		IDLEFolderBean folderBean = new IDLEFolderBean(name);

		SerializationUtils.serialize(
				folderBean,
				idleFolderFile,
				new SerializationListener() {

					@Override
					public void onSerializationSucess() {
					}

					@Override
					public void onSerializationFailed(Exception exception) {
					}
				});
		return new IDLEFolder(folderRoot);
	}
}
