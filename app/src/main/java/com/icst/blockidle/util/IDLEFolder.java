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
import com.icst.blockidle.bean.IDLEJavaFileBean;
import com.icst.blockidle.exception.IDLEFileAlreadyExistsException;
import com.icst.blockidle.listener.SerializationListener;

public class IDLEFolder extends IDLEFile {

	// Contanst for contents of folder
	private static final String CONTENTS = "data";
	private static final String IDLEFOLDER = "IDLEFolder";
	private static final String IDLEFILE = "IDLEFile";

	private IDLEFileBean fileBean;

	public IDLEFolder(File file) {
		super(file);
		fileBean = SerializationUtils.deserialize(new File(file, IDLEFILE), IDLEFolderBean.class);
		if (fileBean == null) {
			fileBean = new IDLEFolderBean(file.getName());
		}
	}

	public IDLEFolder(File file, IDLEFolderBean fileBean) {
		super(file);
		this.fileBean = fileBean;
	}

	public IDLEFolder(IDLEFolder folder, String subFolder) {
		super(new File(new File(folder.file, CONTENTS), subFolder));
		fileBean = SerializationUtils.deserialize(new File(file, IDLEFILE), IDLEFolderBean.class);
		if (fileBean == null) {
			fileBean = new IDLEFolderBean(file.getName());
		}
	}

	public static IDLEFolder getProjectIDLEFolder(ProjectFile projectFile) {
		return new IDLEFolder(projectFile.getFile());
	}

	public List<IDLEFile> getFiles() {
		File contents = new File(file, CONTENTS);
		if (!contents.exists()) {
			return new ArrayList<IDLEFile>();
		}
		ArrayList<IDLEFile> filesList = new ArrayList<IDLEFile>();

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

				filesList.add(new IDLEFolder(file));

			} else if (new File(file, IDLEFILE).exists()) {
				File idleFile = new File(file, IDLEFILE);
				IDLEFileBean idleFileBean = SerializationUtils.deserialize(idleFile, IDLEFileBean.class);

				// Deserialisation failed
				if (idleFileBean == null) {
					continue;
				}

				filesList.add(new IDLEFile(file));
			}
		}

		return filesList;
	}

	public void makeDir() {
		File idleFolderFile = new File(file, IDLEFOLDER);
		File contents = new File(file, CONTENTS);
		contents.mkdirs();
		SerializationUtils.serialize(
				fileBean,
				idleFolderFile,
				new SerializationListener() {

					@Override
					public void onSerializationSucess() {
					}

					@Override
					public void onSerializationFailed(Exception exception) {
					}
				});
	}

	public IDLEFile createJavaFile(String className) throws IDLEFileAlreadyExistsException {
		File contents = new File(file, CONTENTS);
		File folderRoot = new File(contents, className);

		if (!folderRoot.exists()) {
			folderRoot.mkdirs();
		}

		File idleFileBeanFile = new File(folderRoot, IDLEFILE);

		if (idleFileBeanFile.exists() || new File(folderRoot, IDLEFOLDER).exists()) {
			throw new IDLEFileAlreadyExistsException();
		}

		IDLEFileBean fileBean = new IDLEJavaFileBean(className);

		SerializationUtils.serialize(
				fileBean,
				idleFileBeanFile,
				new SerializationListener() {

					@Override
					public void onSerializationSucess() {
					}

					@Override
					public void onSerializationFailed(Exception exception) {
					}
				});
		return new IDLEFile(folderRoot);
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
