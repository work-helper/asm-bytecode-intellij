/*
 *
 *  Copyright 2011 Cédric Champeau
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */

package org.objectweb.asm.idea.service;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.keymap.KeymapManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindowManager;

import org.objectweb.asm.idea.ACodeView;

import reloc.org.objectweb.asm.ClassReader;
import reloc.org.objectweb.asm.ClassVisitor;
import reloc.org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * Created by IntelliJ IDEA.
 * User: cedric
 * Date: 07/01/11
 * Time: 17:07
 */

/**
 * Bytecode view.
 */
public class BytecodeOutline extends ACodeView {

  public BytecodeOutline(final Project project, KeymapManager keymapManager, final ToolWindowManager toolWindowManager) {
    super(toolWindowManager, keymapManager, project);
  }

  public static BytecodeOutline getInstance(Project project) {
    return ServiceManager.getService(project, BytecodeOutline.class);
  }

  public void deCompileAndSetCode(VirtualFile file, StringWriter stringWriter, ClassReader reader, int flags) {
    ClassVisitor visitor = new TraceClassVisitor(new PrintWriter(stringWriter));
    reader.accept(visitor, flags);
    this.setCode(file, stringWriter.toString());
  }
}
