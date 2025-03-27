<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单编号" prop="orderNo">
        <el-input
          v-model="queryParams.orderNo"
          placeholder="请输入订单编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户ID" prop="customerId">
        <el-input
          v-model="queryParams.customerId"
          placeholder="请输入客户ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="支付流水号" prop="payNo">
        <el-input
          v-model="queryParams.payNo"
          placeholder="请输入支付流水号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收货人" prop="consignee">
        <el-input
          v-model="queryParams.consignee"
          placeholder="请输入收货人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否删除：0未删除，1已删除" prop="isDeleted">
        <el-input
          v-model="queryParams.isDeleted"
          placeholder="请输入是否删除：0未删除，1已删除"
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
          v-hasPermi="['order:order:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['order:order:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['order:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['order:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单ID" align="center" prop="id" />
      <el-table-column label="订单编号" align="center" prop="orderNo" />
      <el-table-column label="客户ID" align="center" prop="customerId" />
      <el-table-column label="订单总金额" align="center" prop="totalAmount" />
      <el-table-column label="实付金额" align="center" prop="payAmount" />
      <el-table-column label="优惠金额" align="center" prop="discountAmount" />
      <el-table-column label="运费" align="center" prop="shippingFee" />
      <el-table-column label="支付方式：1支付宝，2微信，3银行卡" align="center" prop="payType" />
      <el-table-column label="支付时间" align="center" prop="payTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.payTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="支付流水号" align="center" prop="payNo" />
      <el-table-column label="订单状态：0待付款，1待发货，2待收货，3已完成，4已取消，5退款中，6已退款" align="center" prop="orderStatus" />
      <el-table-column label="收货人" align="center" prop="consignee" />
      <el-table-column label="联系电话" align="center" prop="mobile" />
      <el-table-column label="收货地址" align="center" prop="address" />
      <el-table-column label="订单备注" align="center" prop="message" />
      <el-table-column label="商家ID" align="center" prop="merchantId" />
      <el-table-column label="是否删除：0未删除，1已删除" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['order:order:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['order:order:remove']">删除</el-button>
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

    <!-- 添加或修改订单信息管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="orderRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单编号" prop="orderNo">
          <el-input v-model="form.orderNo" placeholder="请输入订单编号" />
        </el-form-item>
        <el-form-item label="客户ID" prop="customerId">
          <el-input v-model="form.customerId" placeholder="请输入客户ID" />
        </el-form-item>
        <el-form-item label="订单总金额" prop="totalAmount">
          <el-input v-model="form.totalAmount" placeholder="请输入订单总金额" />
        </el-form-item>
        <el-form-item label="实付金额" prop="payAmount">
          <el-input v-model="form.payAmount" placeholder="请输入实付金额" />
        </el-form-item>
        <el-form-item label="优惠金额" prop="discountAmount">
          <el-input v-model="form.discountAmount" placeholder="请输入优惠金额" />
        </el-form-item>
        <el-form-item label="运费" prop="shippingFee">
          <el-input v-model="form.shippingFee" placeholder="请输入运费" />
        </el-form-item>
        <el-form-item label="支付时间" prop="payTime">
          <el-date-picker clearable
            v-model="form.payTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择支付时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="支付流水号" prop="payNo">
          <el-input v-model="form.payNo" placeholder="请输入支付流水号" />
        </el-form-item>
        <el-form-item label="收货人" prop="consignee">
          <el-input v-model="form.consignee" placeholder="请输入收货人" />
        </el-form-item>
        <el-form-item label="联系电话" prop="mobile">
          <el-input v-model="form.mobile" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="收货地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入收货地址" />
        </el-form-item>
        <el-form-item label="订单备注" prop="message">
          <el-input v-model="form.message" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="商家ID" prop="merchantId">
          <el-input v-model="form.merchantId" placeholder="请输入商家ID" />
        </el-form-item>
        <el-form-item label="是否删除：0未删除，1已删除" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入是否删除：0未删除，1已删除" />
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

<script setup name="Order">
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/order/order";

const { proxy } = getCurrentInstance();

const orderList = ref([]);
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
    orderNo: null,
    customerId: null,
    payType: null,
    payNo: null,
    orderStatus: null,
    consignee: null,
    isDeleted: null,
  },
  rules: {
    orderNo: [
      { required: true, message: "订单编号不能为空", trigger: "blur" }
    ],
    customerId: [
      { required: true, message: "客户ID不能为空", trigger: "blur" }
    ],
    totalAmount: [
      { required: true, message: "订单总金额不能为空", trigger: "blur" }
    ],
    payAmount: [
      { required: true, message: "实付金额不能为空", trigger: "blur" }
    ],
    consignee: [
      { required: true, message: "收货人不能为空", trigger: "blur" }
    ],
    mobile: [
      { required: true, message: "联系电话不能为空", trigger: "blur" }
    ],
    address: [
      { required: true, message: "收货地址不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询订单信息管理列表 */
function getList() {
  loading.value = true;
  listOrder(queryParams.value).then(response => {
    orderList.value = response.rows;
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
    orderNo: null,
    customerId: null,
    totalAmount: null,
    payAmount: null,
    discountAmount: null,
    shippingFee: null,
    payType: null,
    payTime: null,
    payNo: null,
    orderStatus: null,
    consignee: null,
    mobile: null,
    address: null,
    message: null,
    merchantId: null,
    isDeleted: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("orderRef");
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
  title.value = "添加订单信息管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getOrder(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改订单信息管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["orderRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateOrder(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addOrder(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除订单信息管理编号为"' + _ids + '"的数据项？').then(function() {
    return delOrder(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('order/order/export', {
    ...queryParams.value
  }, `order_${new Date().getTime()}.xlsx`)
}

getList();
</script>
