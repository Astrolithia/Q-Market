<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="等级名称" prop="levelName">
        <el-input
          v-model="queryParams.levelName"
          placeholder="请输入等级名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="折扣率" prop="discount">
        <el-input
          v-model="queryParams.discount"
          placeholder="请输入折扣率"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="等级描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入等级描述"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['level:level:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['level:level:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['level:level:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['level:level:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="levelList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="等级ID" align="center" prop="id" />
      <el-table-column label="等级名称" align="center" prop="levelName" />
      <el-table-column label="折扣率" align="center" prop="discount" />
      <el-table-column label="等级描述" align="center" prop="description" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['level:level:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['level:level:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改会员等级管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="levelRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="等级名称" prop="levelName">
          <el-input v-model="form.levelName" placeholder="请输入等级名称" />
        </el-form-item>
        <el-form-item label="最低积分" prop="minPoint">
          <el-input v-model="form.minPoint" placeholder="请输入最低积分" />
        </el-form-item>
        <el-form-item label="最高积分" prop="maxPoint">
          <el-input v-model="form.maxPoint" placeholder="请输入最高积分" />
        </el-form-item>
        <el-form-item label="折扣率" prop="discount">
          <el-input v-model="form.discount" placeholder="请输入折扣率" />
        </el-form-item>
        <el-form-item label="等级描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入等级描述" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Level">
import { listLevel, getLevel, delLevel, addLevel, updateLevel } from "@/api/level/level";

const { proxy } = getCurrentInstance();

const levelList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    levelName: null,
    discount: null,
    description: null,
  },
  rules: {
    levelName: [
      { required: true, message: "等级名称不能为空", trigger: "blur" }
    ],
    minPoint: [
      { required: true, message: "最低积分不能为空", trigger: "blur" }
    ],
    maxPoint: [
      { required: true, message: "最高积分不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询会员等级管理列表 */
function getList() {
  loading.value = true;
  listLevel(queryParams.value).then(response => {
    levelList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    levelName: null,
    minPoint: null,
    maxPoint: null,
    discount: null,
    description: null,
    createTime: null,
    updateTime: null,
    createBy: null,
    updateBy: null
  };
  proxy.resetForm("levelRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加会员等级管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getLevel(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改会员等级管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["levelRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateLevel(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addLevel(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除会员等级管理编号为"' + _ids + '"的数据项？').then(function() {
    return delLevel(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('level/level/export', {
    ...queryParams.value
  }, `level_${new Date().getTime()}.xlsx`)
}

getList();
</script>
